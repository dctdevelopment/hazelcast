/*
 * Copyright (c) 2008-2017, Hazelcast, Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.hazelcast.config;

import java.util.List;
import java.util.concurrent.ScheduledExecutorService;

/**
 * @deprecated this class will be removed in 4.0; it is meant for internal usage only.
 */
class ReplicatedMapConfigReadOnly extends ReplicatedMapConfig {

    public ReplicatedMapConfigReadOnly(ReplicatedMapConfig replicatedMapConfig) {
        super(replicatedMapConfig);
    }

    @Override
    public ReplicatedMapConfig setReplicatorExecutorService(ScheduledExecutorService replicatorExecutorService) {
        throw new UnsupportedOperationException("This config is read-only");
    }

    @Override
    public ReplicatedMapConfig setName(String name) {
        throw new UnsupportedOperationException("This config is read-only");
    }

    @Override
    public ReplicatedMapConfig setReplicationDelayMillis(long replicationDelayMillis) {
        throw new UnsupportedOperationException("This config is read-only");
    }

    @Override
    public ReplicatedMapConfig setConcurrencyLevel(int concurrencyLevel) {
        throw new UnsupportedOperationException("This config is read-only");
    }

    @Override
    public ReplicatedMapConfig setInMemoryFormat(InMemoryFormat inMemoryFormat) {
        throw new UnsupportedOperationException("This config is read-only");
    }

    @Override
    public ReplicatedMapConfig setListenerConfigs(List<ListenerConfig> listenerConfigs) {
        throw new UnsupportedOperationException("This config is read-only");
    }

    @Override
    public void setAsyncFillup(boolean asyncFillup) {
        throw new UnsupportedOperationException("This config is read-only");
    }

    @Override
    public ReplicatedMapConfig setStatisticsEnabled(boolean statisticsEnabled) {
        throw new UnsupportedOperationException("This config is read-only");
    }

    @Override
    public ReplicatedMapConfig setMergePolicy(String mergePolicy) {
        throw new UnsupportedOperationException("This config is read-only");
    }
}
