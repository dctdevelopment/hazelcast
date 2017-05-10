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

import com.hazelcast.client.impl.protocol.ClientMessage;
import com.hazelcast.internal.networking.ChannelOutboundHandler;
import com.hazelcast.internal.networking.ChannelWriter;
import com.hazelcast.internal.networking.ChannelWriterInitializer;
import com.hazelcast.internal.networking.InitResult;
import com.hazelcast.logging.Logger;
import com.hazelcast.nio.IOUtil;
import com.hazelcast.nio.Protocols;

import java.nio.ByteBuffer;

class ClientChannelWriterInitializer implements ChannelWriterInitializer<ClientConnection> {

    private final int bufferSize;
    private final boolean direct;

    ClientChannelWriterInitializer(int bufferSize, boolean direct) {
        this.bufferSize = bufferSize;
        this.direct = direct;
    }

    @Override
    public InitResult<ChannelOutboundHandler> init(ClientConnection connection, ChannelWriter writer, String protocol) {
        Logger.getLogger(getClass())
                .fine("Initializing ClientSocketWriter ChannelOutboundHandler with " + Protocols.toUserFriendlyString(protocol));

        ByteBuffer outputBuffer = IOUtil.newByteBuffer(bufferSize, direct);

        ChannelOutboundHandler handler = new ChannelOutboundHandler<ClientMessage>() {
            @Override
            public boolean onWrite(ClientMessage msg, ByteBuffer dst) throws Exception {
                return msg.writeTo(dst);
            }
        };

        return new InitResult<ChannelOutboundHandler>(outputBuffer, handler);
    }
}
