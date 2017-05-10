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

package com.hazelcast.client.connection.nio;

import com.hazelcast.client.connection.ClientConnectionManager;
import com.hazelcast.client.impl.protocol.ClientMessage;
import com.hazelcast.client.impl.protocol.util.ClientMessageChannelInboundHandler;
import com.hazelcast.internal.networking.ChannelInboundHandler;
import com.hazelcast.internal.networking.ChannelReader;
import com.hazelcast.internal.networking.ChannelReaderInitializer;
import com.hazelcast.internal.networking.InitResult;
import com.hazelcast.nio.IOUtil;

import java.io.IOException;
import java.nio.ByteBuffer;

class ClientChannelReaderInitializer implements ChannelReaderInitializer<ClientConnection> {

    private final int bufferSize;
    private final boolean direct;

    ClientChannelReaderInitializer(int bufferSize, boolean direct) {
        this.bufferSize = bufferSize;
        this.direct = direct;
    }

    @Override
    public InitResult<ChannelInboundHandler> init(final ClientConnection connection, ChannelReader reader) throws IOException {
        ByteBuffer inputBuffer = IOUtil.newByteBuffer(bufferSize, direct);

        ChannelInboundHandler inboundHandler = new ClientMessageChannelInboundHandler(reader.getNormalFramesReadCounter(),
                new ClientMessageChannelInboundHandler.MessageHandler() {
                    private final ClientConnectionManager connectionManager = connection.getConnectionManager();

                    @Override
                    public void handleMessage(ClientMessage message) {
                        connectionManager.handleClientMessage(message, connection);
                    }
                });
        return new InitResult<ChannelInboundHandler>(inputBuffer, inboundHandler);
    }
}
