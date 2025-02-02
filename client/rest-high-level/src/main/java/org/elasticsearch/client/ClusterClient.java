/*
 * Copyright Elasticsearch B.V. and/or licensed to Elasticsearch B.V. under one
 * or more contributor license agreements. Licensed under the Elastic License
 * 2.0 and the Server Side Public License, v 1; you may not use this file except
 * in compliance with, at your election, the Elastic License 2.0 or the Server
 * Side Public License, v 1.
 */

package org.elasticsearch.client;

import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.admin.cluster.health.ClusterHealthRequest;
import org.elasticsearch.action.admin.cluster.health.ClusterHealthResponse;
import org.elasticsearch.action.admin.cluster.settings.ClusterGetSettingsRequest;
import org.elasticsearch.action.admin.cluster.settings.ClusterGetSettingsResponse;
import org.elasticsearch.action.admin.cluster.settings.ClusterUpdateSettingsRequest;
import org.elasticsearch.action.admin.cluster.settings.ClusterUpdateSettingsResponse;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.cluster.RemoteInfoRequest;
import org.elasticsearch.client.cluster.RemoteInfoResponse;
import org.elasticsearch.client.indices.ComponentTemplatesExistRequest;
import org.elasticsearch.client.indices.DeleteComponentTemplateRequest;
import org.elasticsearch.client.indices.GetComponentTemplatesRequest;
import org.elasticsearch.client.indices.GetComponentTemplatesResponse;
import org.elasticsearch.client.indices.PutComponentTemplateRequest;
import org.elasticsearch.rest.RestStatus;

import java.io.IOException;

import static java.util.Collections.emptySet;
import static java.util.Collections.singleton;

/**
 * A wrapper for the {@link RestHighLevelClient} that provides methods for accessing the Cluster API.
 * <p>
 * See <a href="https://www.elastic.co/guide/en/elasticsearch/reference/current/cluster.html">Cluster API on elastic.co</a>
 */
public final class ClusterClient extends RestHighLevelClient{
//    private final RestHighLevelClient restHighLevelClient;

//    ClusterClient(RestHighLevelClient restHighLevelClient) {
//        this.restHighLevelClient = restHighLevelClient;
//    }

    /**
     * Updates cluster wide specific settings using the Cluster Update Settings API.
     * See <a href="https://www.elastic.co/guide/en/elasticsearch/reference/current/cluster-update-settings.html"> Cluster Update Settings
     * API on elastic.co</a>
     * @param clusterUpdateSettingsRequest the request
     * @param options the request options (e.g. headers), use {@link RequestOptions#DEFAULT} if nothing needs to be customized
     * @return the response
     * @throws IOException in case there is a problem sending the request or parsing back the response
     */
    public ClusterUpdateSettingsResponse putSettings(ClusterUpdateSettingsRequest clusterUpdateSettingsRequest, RequestOptions options)
            throws IOException {
        return performRequestAndParseEntity(clusterUpdateSettingsRequest, ClusterRequestConverters::clusterPutSettings,
                options, ClusterUpdateSettingsResponse::fromXContent, emptySet());
    }

    /**
     * Asynchronously updates cluster wide specific settings using the Cluster Update Settings API.
     * See <a href="https://www.elastic.co/guide/en/elasticsearch/reference/current/cluster-update-settings.html"> Cluster Update Settings
     * API on elastic.co</a>
     * @param clusterUpdateSettingsRequest the request
     * @param options the request options (e.g. headers), use {@link RequestOptions#DEFAULT} if nothing needs to be customized
     * @param listener the listener to be notified upon request completion
     * @return cancellable that may be used to cancel the request
     */
    public Cancellable putSettingsAsync(ClusterUpdateSettingsRequest clusterUpdateSettingsRequest, RequestOptions options,
                                        ActionListener<ClusterUpdateSettingsResponse> listener) {
        return performRequestAsyncAndParseEntity(clusterUpdateSettingsRequest,
                ClusterRequestConverters::clusterPutSettings,
                options, ClusterUpdateSettingsResponse::fromXContent, listener, emptySet());
    }

    /**
     * Get the cluster wide settings using the Cluster Get Settings API.
     * See <a href="https://www.elastic.co/guide/en/elasticsearch/reference/current/cluster-get-settings.html"> Cluster Get Settings
     * API on elastic.co</a>
     * @param clusterGetSettingsRequest the request
     * @param options the request options (e.g. headers), use {@link RequestOptions#DEFAULT} if nothing needs to be customized
     * @return the response
     * @throws IOException in case there is a problem sending the request or parsing back the response
     */
    public ClusterGetSettingsResponse getSettings(ClusterGetSettingsRequest clusterGetSettingsRequest, RequestOptions options)
        throws IOException {
        return performRequestAndParseEntity(clusterGetSettingsRequest, ClusterRequestConverters::clusterGetSettings,
            options, ClusterGetSettingsResponse::fromXContent, emptySet());
    }

    /**
     * Asynchronously get the cluster wide settings using the Cluster Get Settings API.
     * See <a href="https://www.elastic.co/guide/en/elasticsearch/reference/current/cluster-get-settings.html"> Cluster Get Settings
     * API on elastic.co</a>
     * @param clusterGetSettingsRequest the request
     * @param options the request options (e.g. headers), use {@link RequestOptions#DEFAULT} if nothing needs to be customized
     * @param listener the listener to be notified upon request completion
     * @return cancellable that may be used to cancel the request
     */
    public Cancellable getSettingsAsync(ClusterGetSettingsRequest clusterGetSettingsRequest, RequestOptions options,
                                        ActionListener<ClusterGetSettingsResponse> listener) {
        return performRequestAsyncAndParseEntity(
            clusterGetSettingsRequest, ClusterRequestConverters::clusterGetSettings,
            options, ClusterGetSettingsResponse::fromXContent, listener, emptySet());
    }

    /**
     * Get cluster health using the Cluster Health API.
     * See
     * <a href="https://www.elastic.co/guide/en/elasticsearch/reference/current/cluster-health.html"> Cluster Health API on elastic.co</a>
     * <p>
     * If timeout occurred, {@link ClusterHealthResponse} will have isTimedOut() == true and status() == RestStatus.REQUEST_TIMEOUT
     * @param healthRequest the request
     * @param options the request options (e.g. headers), use {@link RequestOptions#DEFAULT} if nothing needs to be customized
     * @return the response
     * @throws IOException in case there is a problem sending the request or parsing back the response
     */
    public ClusterHealthResponse health(ClusterHealthRequest healthRequest, RequestOptions options) throws IOException {
        return performRequestAndParseEntity(healthRequest, ClusterRequestConverters::clusterHealth, options,
                ClusterHealthResponse::fromXContent, singleton(RestStatus.REQUEST_TIMEOUT.getStatus()));
    }

    /**
     * Asynchronously get cluster health using the Cluster Health API.
     * See
     * <a href="https://www.elastic.co/guide/en/elasticsearch/reference/current/cluster-health.html"> Cluster Health API on elastic.co</a>
     * If timeout occurred, {@link ClusterHealthResponse} will have isTimedOut() == true and status() == RestStatus.REQUEST_TIMEOUT
     * @param healthRequest the request
     * @param options the request options (e.g. headers), use {@link RequestOptions#DEFAULT} if nothing needs to be customized
     * @param listener the listener to be notified upon request completion
     * @return cancellable that may be used to cancel the request
     */
    public Cancellable healthAsync(ClusterHealthRequest healthRequest, RequestOptions options,
                                   ActionListener<ClusterHealthResponse> listener) {
        return performRequestAsyncAndParseEntity(healthRequest, ClusterRequestConverters::clusterHealth, options,
                ClusterHealthResponse::fromXContent, listener, singleton(RestStatus.REQUEST_TIMEOUT.getStatus()));
    }

    /**
     * Get the remote cluster information using the Remote cluster info API.
     * See <a href="https://www.elastic.co/guide/en/elasticsearch/reference/current/cluster-remote-info.html"> Remote cluster info
     * API on elastic.co</a>
     * @param request the request
     * @param options the request options (e.g. headers), use {@link RequestOptions#DEFAULT} if nothing needs to be customized
     * @return the response
     * @throws IOException in case there is a problem sending the request or parsing back the response
     */
    public RemoteInfoResponse remoteInfo(RemoteInfoRequest request, RequestOptions options) throws IOException {
        return performRequestAndParseEntity(request, ClusterRequestConverters::remoteInfo, options,
                RemoteInfoResponse::fromXContent, singleton(RestStatus.REQUEST_TIMEOUT.getStatus()));
    }

    /**
     * Asynchronously get remote cluster information using the Remote cluster info API.
     * See <a href="https://www.elastic.co/guide/en/elasticsearch/reference/current/cluster-remote-info.html"> Remote cluster info
     * API on elastic.co</a>
     * @param request the request
     * @param options the request options (e.g. headers), use {@link RequestOptions#DEFAULT} if nothing needs to be customized
     * @param listener the listener to be notified upon request completion
     * @return cancellable that may be used to cancel the request
     */
    public Cancellable remoteInfoAsync(RemoteInfoRequest request, RequestOptions options,
                                       ActionListener<RemoteInfoResponse> listener) {
        return performRequestAsyncAndParseEntity(request, ClusterRequestConverters::remoteInfo, options,
                RemoteInfoResponse::fromXContent, listener, singleton(RestStatus.REQUEST_TIMEOUT.getStatus()));
    }

    /**
     * Delete a component template using the Component Templates API
     *
     * @param req the request
     * @param options the request options (e.g. headers), use {@link RequestOptions#DEFAULT} if nothing needs to be customized
     * @throws IOException in case there is a problem sending the request or parsing back the response
     */
    public AcknowledgedResponse deleteComponentTemplate(DeleteComponentTemplateRequest req, RequestOptions options) throws IOException {
        return performRequestAndParseEntity(req, ClusterRequestConverters::deleteComponentTemplate,
            options, AcknowledgedResponse::fromXContent, emptySet());
    }

    /**
     * Asynchronously delete a component template using the Component Templates API
     *
     * @param request  the request
     * @param options  the request options (e.g. headers), use {@link RequestOptions#DEFAULT} if nothing needs to be customized
     * @param listener the listener to be notified upon request completion
     * @return cancellable that may be used to cancel the request
     */
    public Cancellable deleteComponentTemplateAsync(DeleteComponentTemplateRequest request, RequestOptions options,
                                                    ActionListener<AcknowledgedResponse> listener) {
        return performRequestAsyncAndParseEntity(request, ClusterRequestConverters::deleteComponentTemplate,
            options, AcknowledgedResponse::fromXContent, listener, emptySet());
    }

    /**
     * Puts a component template using the Component Templates API.
     *
     * @param putComponentTemplateRequest the request
     * @param options the request options (e.g. headers), use {@link RequestOptions#DEFAULT} if nothing needs to be customized
     * @return the response
     * @throws IOException in case there is a problem sending the request or parsing back the response
     */
    public AcknowledgedResponse putComponentTemplate(PutComponentTemplateRequest putComponentTemplateRequest,
                                                     RequestOptions options) throws IOException {
        return performRequestAndParseEntity(putComponentTemplateRequest, ClusterRequestConverters::putComponentTemplate,
            options, AcknowledgedResponse::fromXContent, emptySet());
    }

    /**
     * Asynchronously puts a component template using the Component Templates API.
     *
     * @param putComponentTemplateRequest the request
     * @param options the request options (e.g. headers), use {@link RequestOptions#DEFAULT} if nothing needs to be customized
     * @param listener the listener to be notified upon request completion
     * @return cancellable that may be used to cancel the request
     */
    public Cancellable putComponentTemplateAsync(PutComponentTemplateRequest putComponentTemplateRequest,
                                                 RequestOptions options, ActionListener<AcknowledgedResponse> listener) {
        return performRequestAsyncAndParseEntity(putComponentTemplateRequest,
            ClusterRequestConverters::putComponentTemplate, options, AcknowledgedResponse::fromXContent, listener, emptySet());
    }

    /**
     * Gets component templates using the Components Templates API
     * @param options the request options (e.g. headers), use {@link RequestOptions#DEFAULT} if nothing needs to be customized
     * @param getComponentTemplatesRequest the request
     * @return the response
     * @throws IOException in case there is a problem sending the request or parsing back the response
     */
    public GetComponentTemplatesResponse getComponentTemplate(GetComponentTemplatesRequest getComponentTemplatesRequest,
                                                              RequestOptions options) throws IOException {
        return performRequestAndParseEntity(getComponentTemplatesRequest,
            ClusterRequestConverters::getComponentTemplates, options, GetComponentTemplatesResponse::fromXContent, emptySet());
    }

    /**
     * Asynchronously gets component templates using the Components Templates API
     * @param getComponentTemplatesRequest the request
     * @param options the request options (e.g. headers), use {@link RequestOptions#DEFAULT} if nothing needs to be customized
     * @param listener the listener to be notified upon request completion
     * @return cancellable that may be used to cancel the request
     */
    public Cancellable getComponentTemplateAsync(GetComponentTemplatesRequest getComponentTemplatesRequest, RequestOptions options,
                                                 ActionListener<GetComponentTemplatesResponse> listener) {
        return performRequestAsyncAndParseEntity(getComponentTemplatesRequest,
            ClusterRequestConverters::getComponentTemplates, options, GetComponentTemplatesResponse::fromXContent, listener, emptySet());
    }

    /**
     * Uses the Component Templates API to determine if component templates exist
     *
     * @param componentTemplatesRequest the request
     * @param options the request options (e.g. headers), use {@link RequestOptions#DEFAULT} if nothing needs to be customized
     * @return true if any index templates in the request exist, false otherwise
     * @throws IOException in case there is a problem sending the request or parsing back the response
     */
    public boolean existsComponentTemplate(ComponentTemplatesExistRequest componentTemplatesRequest,
                                           RequestOptions options) throws IOException {
        return performRequest(componentTemplatesRequest,
            ClusterRequestConverters::componentTemplatesExist, options, RestHighLevelClient::convertExistsResponse, emptySet());
    }

    /**
     * Uses the Index Templates API to determine if index templates exist
     * @param componentTemplatesRequest the request
     * @param options the request options (e.g. headers), use {@link RequestOptions#DEFAULT} if nothing needs to be customized
     * @param listener the listener to be notified upon request completion. The listener will be called with the value {@code true}
     * @return cancellable that may be used to cancel the request
     */
    public Cancellable existsComponentTemplateAsync(ComponentTemplatesExistRequest componentTemplatesRequest,
                                                    RequestOptions options,
                                                    ActionListener<Boolean> listener) {

        return performRequestAsync(componentTemplatesRequest,
            ClusterRequestConverters::componentTemplatesExist, options, RestHighLevelClient::convertExistsResponse, listener, emptySet());
    }
}
