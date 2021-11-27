/*
 * Copyright Elasticsearch B.V. and/or licensed to Elasticsearch B.V. under one
 * or more contributor license agreements. Licensed under the Elastic License
 * 2.0 and the Server Side Public License, v 1; you may not use this file except
 * in compliance with, at your election, the Elastic License 2.0 or the Server
 * Side Public License, v 1.
 */

package org.elasticsearch.index.reindex.remote;

import org.elasticsearch.common.xcontent.ParseField;
import org.elasticsearch.common.ParsingException;
import org.elasticsearch.common.util.concurrent.EsRejectedExecutionException;
import org.elasticsearch.common.xcontent.ObjectParser;
import org.elasticsearch.common.xcontent.XContentLocation;
import org.elasticsearch.common.xcontent.XContentParser;
import java.util.function.BiFunction;
import static java.util.Objects.requireNonNull;


/**
 * Collects stuff about Throwables and attempts to rebuild them.
 */
public class ThrowableBuilder {
    public static final BiFunction<XContentParser, Void, Throwable> PARSER;
    static {
        ObjectParser<ThrowableBuilder, Void> parser = new ObjectParser<>("reason", true, ThrowableBuilder::new);
        PARSER = parser.andThen(ThrowableBuilder::build);
        parser.declareString(ThrowableBuilder::setType, new ParseField("type"));
        parser.declareString(ThrowableBuilder::setReason, new ParseField("reason"));
        parser.declareObject(ThrowableBuilder::setCausedBy, PARSER::apply, new ParseField("caused_by"));

        // So we can give a nice error for parsing exceptions
        parser.declareInt(ThrowableBuilder::setLine, new ParseField("line"));
        parser.declareInt(ThrowableBuilder::setColumn, new ParseField("col"));
    }

    private String type;
    private String reason;
    private Integer line;
    private Integer column;
    private Throwable causedBy;

    public Throwable build() {
        Throwable t = buildWithoutCause();
        if (causedBy != null) {
            t.initCause(causedBy);
        }
        return t;
    }

    private Throwable buildWithoutCause() {
        requireNonNull(type, "[type] is required");
        requireNonNull(reason, "[reason] is required");
        switch (type) {
        // Make some effort to use the right exceptions
        case "es_rejected_execution_exception":
            return new EsRejectedExecutionException(reason);
        case "parsing_exception":
            XContentLocation location = null;
            if (line != null && column != null) {
                location = new XContentLocation(line, column);
            }
            return new ParsingException(location, reason);
        // But it isn't worth trying to get it perfect....
        default:
            return new RuntimeException(type + ": " + reason);
        }
    }

    public void setType(String type) {
        this.type = type;
    }
    public void setReason(String reason) {
        this.reason = reason;
    }
    public void setLine(Integer line) {
        this.line = line;
    }
    public void setColumn(Integer column) {
        this.column = column;
    }
    public void setCausedBy(Throwable causedBy) {
        this.causedBy = causedBy;
    }
}
