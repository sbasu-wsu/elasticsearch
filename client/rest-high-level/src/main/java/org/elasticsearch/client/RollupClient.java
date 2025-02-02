/*
 * Copyright Elasticsearch B.V. and/or licensed to Elasticsearch B.V. under one
 * or more contributor license agreements. Licensed under the Elastic License
 * 2.0 and the Server Side Public License, v 1; you may not use this file except
 * in compliance with, at your election, the Elastic License 2.0 or the Server
 * Side Public License, v 1.
 */

package org.elasticsearch.client;

import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.core.AcknowledgedResponse;
import org.elasticsearch.client.rollup.DeleteRollupJobRequest;
import org.elasticsearch.client.rollup.GetRollupIndexCapsRequest;
import org.elasticsearch.client.rollup.GetRollupIndexCapsResponse;
import org.elasticsearch.client.rollup.GetRollupJobRequest;
import org.elasticsearch.client.rollup.GetRollupJobResponse;
import org.elasticsearch.client.rollup.GetRollupCapsRequest;
import org.elasticsearch.client.rollup.GetRollupCapsResponse;
import org.elasticsearch.client.rollup.PutRollupJobRequest;
import org.elasticsearch.client.rollup.StartRollupJobRequest;
import org.elasticsearch.client.rollup.StartRollupJobResponse;
import org.elasticsearch.client.rollup.StopRollupJobRequest;
import org.elasticsearch.client.rollup.StopRollupJobResponse;

import java.io.IOException;
import java.util.Collections;

/**
 * A wrapper for the {@link RestHighLevelClient} that provides methods for
 * accessing the Elastic Rollup-related methods
 * <p>
 * See the <a href="https://www.elastic.co/guide/en/elasticsearch/reference/current/rollup-apis.html">
 * X-Pack Rollup APIs on elastic.co</a> for more information.
 */
public class RollupClient extends RestHighLevelClient{

    /**
     * Put a rollup job into the cluster
     * See <a href="https://www.elastic.co/guide/en/elasticsearch/reference/current/rollup-put-job.html">
     * the docs</a> for more.
     * @param request the request
     * @param options the request options (e.g. headers), use {@link RequestOptions#DEFAULT} if nothing needs to be customized
     * @return the response
     * @throws IOException in case there is a problem sending the request or parsing back the response
     */
    public AcknowledgedResponse putRollupJob(PutRollupJobRequest request, RequestOptions options) throws IOException {
        return performRequestAndParseEntity(request,
            RollupRequestConverters::putJob,
            options,
            AcknowledgedResponse::fromXContent,
            Collections.emptySet());
    }

    /**
     * Asynchronously put a rollup job into the cluster
     * See <a href="https://www.elastic.co/guide/en/elasticsearch/reference/current/rollup-put-job.html">
     * the docs</a> for more.
     * @param request the request
     * @param options the request options (e.g. headers), use {@link RequestOptions#DEFAULT} if nothing needs to be customized
     * @param listener the listener to be notified upon request completion
     * @return cancellable that may be used to cancel the request
     */
    public Cancellable putRollupJobAsync(PutRollupJobRequest request, RequestOptions options,
                                         ActionListener<AcknowledgedResponse> listener) {
       return performRequestAsyncAndParseEntity(request,
            RollupRequestConverters::putJob,
            options,
            AcknowledgedResponse::fromXContent,
            listener, Collections.emptySet());
    }

    /**
     * Start a rollup job
     * See <a href="https://www.elastic.co/guide/en/elasticsearch/reference/current/rollup-start-job.html">
     * the docs</a> for more.
     * @param request the request
     * @param options the request options (e.g. headers), use {@link RequestOptions#DEFAULT} if nothing needs to be customized
     * @return the response
     * @throws IOException in case there is a problem sending the request or parsing back the response
     */
    public StartRollupJobResponse startRollupJob(StartRollupJobRequest request, RequestOptions options)  throws IOException {
        return performRequestAndParseEntity(request,
            RollupRequestConverters::startJob,
            options,
            StartRollupJobResponse::fromXContent,
            Collections.emptySet());
    }

    /**
     * Asynchronously start a rollup job
     * See <a href="https://www.elastic.co/guide/en/elasticsearch/reference/current/rollup-start-job.html">
     * the docs</a> for more.
     * @param request the request
     * @param options the request options (e.g. headers), use {@link RequestOptions#DEFAULT} if nothing needs to be customized
     * @param listener the listener to be notified upon request completion
     * @return cancellable that may be used to cancel the request
     */
    public Cancellable startRollupJobAsync(StartRollupJobRequest request, RequestOptions options,
                                           ActionListener<StartRollupJobResponse> listener) {
        return performRequestAsyncAndParseEntity(request,
            RollupRequestConverters::startJob,
            options,
            StartRollupJobResponse::fromXContent,
            listener, Collections.emptySet());
    }

    /**
     * Stop a rollup job
     * See <a href="https://www.elastic.co/guide/en/elasticsearch/reference/current/rollup-stop-job.html">
     * the docs</a> for more.
     * @param request the request
     * @param options the request options (e.g. headers), use {@link RequestOptions#DEFAULT} if nothing needs to be customized
     * @return the response
     * @throws IOException in case there is a problem sending the request or parsing back the response
     */
    public StopRollupJobResponse stopRollupJob(StopRollupJobRequest request, RequestOptions options)  throws IOException {
        return performRequestAndParseEntity(request,
            RollupRequestConverters::stopJob,
            options,
            StopRollupJobResponse::fromXContent,
            Collections.emptySet());
    }

    /**
     * Asynchronously stop a rollup job
     * See <a href="https://www.elastic.co/guide/en/elasticsearch/reference/current/rollup-stop-job.html">
     * the docs</a> for more.
     * @param request the request
     * @param options the request options (e.g. headers), use {@link RequestOptions#DEFAULT} if nothing needs to be customized
     * @param listener the listener to be notified upon request completion
     * @return cancellable that may be used to cancel the request
     */
    public Cancellable stopRollupJobAsync(StopRollupJobRequest request, RequestOptions options,
                                          ActionListener<StopRollupJobResponse> listener) {
        return performRequestAsyncAndParseEntity(request,
            RollupRequestConverters::stopJob,
            options,
            StopRollupJobResponse::fromXContent,
            listener, Collections.emptySet());
    }

    /**
     * Delete a rollup job from the cluster
     * See <a href="https://www.elastic.co/guide/en/elasticsearch/reference/current/rollup-delete-job.html">
     * the docs</a> for more.
     * @param request the request
     * @param options the request options (e.g. headers), use {@link RequestOptions#DEFAULT} if nothing needs to be customized
     * @return the response
     * @throws IOException in case there is a problem sending the request or parsing back the response
     */
    public AcknowledgedResponse deleteRollupJob(DeleteRollupJobRequest request, RequestOptions options) throws IOException {
        return performRequestAndParseEntity(request,
            RollupRequestConverters::deleteJob,
            options,
            AcknowledgedResponse::fromXContent,
            Collections.emptySet());
    }
    /**
     * Asynchronously delete a rollup job from the cluster
     * See <a href="https://www.elastic.co/guide/en/elasticsearch/reference/current/rollup-delete-job.html">
     *     The docs</a> for details.
     * @param request the request
     * @param options the request options (e.g. headers), use {@link RequestOptions#DEFAULT} if nothing needs to be customized
     * @param listener the listener to be notified upon request completion
     * @return cancellable that may be used to cancel the request
     */
    public Cancellable deleteRollupJobAsync(DeleteRollupJobRequest request,
                                            RequestOptions options,
                                            ActionListener<AcknowledgedResponse> listener) {
        return performRequestAsyncAndParseEntity(request,
            RollupRequestConverters::deleteJob,
            options,
            AcknowledgedResponse::fromXContent,
            listener, Collections.emptySet());
    }

    /**
     * Get a rollup job from the cluster.
     * See <a href="https://www.elastic.co/guide/en/elasticsearch/reference/current/rollup-put-job.html">
     * the docs</a> for more.
     * @param request the request
     * @param options the request options (e.g. headers), use {@link RequestOptions#DEFAULT} if nothing needs to be customized
     * @return the response
     * @throws IOException in case there is a problem sending the request or parsing back the response
     */
    public GetRollupJobResponse getRollupJob(GetRollupJobRequest request, RequestOptions options) throws IOException {
        return performRequestAndParseEntity(request,
            RollupRequestConverters::getJob,
            options,
            GetRollupJobResponse::fromXContent,
            Collections.emptySet());
    }

    /**
     * Asynchronously get a rollup job from the cluster.
     * See <a href="https://www.elastic.co/guide/en/elasticsearch/reference/current/rollup-put-job.html">
     * the docs</a> for more.
     * @param request the request
     * @param options the request options (e.g. headers), use {@link RequestOptions#DEFAULT} if nothing needs to be customized
     * @param listener the listener to be notified upon request completion
     * @return cancellable that may be used to cancel the request
     */
    public Cancellable getRollupJobAsync(GetRollupJobRequest request, RequestOptions options,
                                         ActionListener<GetRollupJobResponse> listener) {
        return performRequestAsyncAndParseEntity(request,
            RollupRequestConverters::getJob,
            options,
            GetRollupJobResponse::fromXContent,
            listener, Collections.emptySet());
    }

    /**
     * Perform a rollup search.
     * See <a href="https://www.elastic.co/guide/en/elasticsearch/reference/current/rollup-search.html">
     * the docs</a> for more.
     * @param request the request
     * @param options the request options (e.g. headers), use {@link RequestOptions#DEFAULT} if nothing needs to be customized
     * @return the response
     * @throws IOException in case there is a problem sending the request or parsing back the response
     */
    public SearchResponse search(SearchRequest request, RequestOptions options) throws IOException {
        return performRequestAndParseEntity(
                request,
                RollupRequestConverters::search,
                options,
                SearchResponse::fromXContent,
                Collections.emptySet());
    }

    /**
     * Perform a rollup search.
     * See <a href="https://www.elastic.co/guide/en/elasticsearch/reference/current/rollup-search.html">
     * the docs</a> for more.
     * @param request the request
     * @param options the request options (e.g. headers), use {@link RequestOptions#DEFAULT} if nothing needs to be customized
     * @param listener the listener to be notified upon request completion
     * @return cancellable that may be used to cancel the request
     */
    public Cancellable searchAsync(SearchRequest request, RequestOptions options, ActionListener<SearchResponse> listener) {
        return performRequestAsyncAndParseEntity(
                request,
                RollupRequestConverters::search,
                options,
                SearchResponse::fromXContent,
                listener,
                Collections.emptySet());
    }

    /**
     * Get the Rollup Capabilities of a target (non-rollup) index or pattern
     * See <a href="https://www.elastic.co/guide/en/elasticsearch/reference/master/rollup-get-rollup-caps.html">
     * the docs</a> for more.
     * @param request the request
     * @param options the request options (e.g. headers), use {@link RequestOptions#DEFAULT} if nothing needs to be customized
     * @return the response
     * @throws IOException in case there is a problem sending the request or parsing back the response
     */
    public GetRollupCapsResponse getRollupCapabilities(GetRollupCapsRequest request, RequestOptions options) throws IOException {
        return performRequestAndParseEntity(request,
            RollupRequestConverters::getRollupCaps,
            options,
            GetRollupCapsResponse::fromXContent,
            Collections.emptySet());
    }

    /**
     * Asynchronously Get the Rollup Capabilities of a target (non-rollup) index or pattern
     * See <a href="https://www.elastic.co/guide/en/elasticsearch/reference/current/rollup-put-job.html">
     * the docs</a> for more.
     * @param request the request
     * @param options the request options (e.g. headers), use {@link RequestOptions#DEFAULT} if nothing needs to be customized
     * @param listener the listener to be notified upon request completion
     * @return cancellable that may be used to cancel the request
     */
    public Cancellable getRollupCapabilitiesAsync(GetRollupCapsRequest request, RequestOptions options,
                                                  ActionListener<GetRollupCapsResponse> listener) {
        return performRequestAsyncAndParseEntity(request,
            RollupRequestConverters::getRollupCaps,
            options,
            GetRollupCapsResponse::fromXContent,
            listener,
            Collections.emptySet());
    }

    /**
     * Get the Rollup Index Capabilities of a rollup index or pattern
     * See <a href="https://www.elastic.co/guide/en/elasticsearch/reference/master/rollup-get-rollup-index-caps.html">
     * the docs</a> for more.
     * @param request the request
     * @param options the request options (e.g. headers), use {@link RequestOptions#DEFAULT} if nothing needs to be customized
     * @return the response
     * @throws IOException in case there is a problem sending the request or parsing back the response
     */
    public GetRollupIndexCapsResponse getRollupIndexCapabilities(GetRollupIndexCapsRequest request,
                                                                 RequestOptions options) throws IOException {
        return performRequestAndParseEntity(request,
            RollupRequestConverters::getRollupIndexCaps,
            options,
            GetRollupIndexCapsResponse::fromXContent,
            Collections.emptySet());
    }

    /**
     * Asynchronously Get the Rollup Index Capabilities of a rollup index or pattern
     * See <a href="https://www.elastic.co/guide/en/elasticsearch/reference/master/rollup-get-rollup-index-caps.html">
     * the docs</a> for more.
     * @param request the request
     * @param options the request options (e.g. headers), use {@link RequestOptions#DEFAULT} if nothing needs to be customized
     * @param listener the listener to be notified upon request completion
     * @return cancellable that may be used to cancel the request
     */
    public Cancellable getRollupIndexCapabilitiesAsync(GetRollupIndexCapsRequest request, RequestOptions options,
                                                       ActionListener<GetRollupIndexCapsResponse> listener) {
        return performRequestAsyncAndParseEntity(request,
            RollupRequestConverters::getRollupIndexCaps,
            options,
            GetRollupIndexCapsResponse::fromXContent,
            listener,
            Collections.emptySet());
    }
}
