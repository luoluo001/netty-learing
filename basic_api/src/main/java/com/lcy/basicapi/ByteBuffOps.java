package com.lcy.basicapi;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.buffer.UnpooledByteBufAllocator;
import org.junit.Test;

/**
 * Created by luo on 2020/1/5.
 */
public class ByteBuffOps {
    
    @Test
    public void testPool(){
        UnpooledByteBufAllocator allocator = UnpooledByteBufAllocator.DEFAULT;
        ByteBuf byteBuf = PooledByteBufAllocator.DEFAULT.directBuffer(100);

    }
}
