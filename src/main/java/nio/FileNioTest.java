package nio;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class FileNioTest {
    public static void main(String[] args)  throws IOException {

//        RandomAccessFile aFile = new RandomAccessFile("data/nio-data.txt", "rw");
        RandomAccessFile aFile = new RandomAccessFile("./pom.xml", "rw");
        FileChannel inChannel = aFile.getChannel();

        ByteBuffer buf = ByteBuffer.allocate(148);

        int bytesRead = inChannel.read(buf); //把channel的数据写入到 buffer
        while (bytesRead != -1) {

            System.out.println("Read " + bytesRead);
            // 注意 buf.flip() 的调用，首先读取数据到Buffer，然后反转Buffer,接着再从Buffer中读取数据。
            buf.flip();
            while(buf.hasRemaining()){
                System.out.print((char) buf.get());
            }

            buf.clear();
            bytesRead = inChannel.read(buf);
        }
        aFile.close();

    }
}
