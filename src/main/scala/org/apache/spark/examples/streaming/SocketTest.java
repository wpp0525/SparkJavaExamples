package org.apache.spark.examples.streaming;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;

public class SocketTest {

    public static void main(String[] args)  throws IOException {
        //1.创建一个Server Socket
        Socket socket = new Socket();

        // 2.连接到指定的 server socket,指定IP 和端口号
//        InetSocketAddress address = new InetSocketAddress("crm-master1",18824);
        InetSocketAddress address = new InetSocketAddress("crm-master1", 9999);
        try {
            socket.connect(address);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 3.连接成功后，获取相应的输入输出流，进行数据交互
        BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);

       BufferedReader keyword = new BufferedReader(new InputStreamReader(System.in));

        while(true) {
            // 捕捉来自服务器端发来的消息   服务器端没有发消息过来时，br.ready() 为false， 循环检测是否有数据，有测打印出来
            if(br.ready()) {
                String info = br.readLine();
                System.out.println("Server:"+info);
            }

            //捕获来自服务器端另外一个输入流 : 键盘的数据。如果有数据，则发送给服务器端
            if(keyword.ready())
            {
                String  test = keyword.readLine();
                pw.println(test);
                System.out.println("Client:"+test);
            }

        }

    }
}
