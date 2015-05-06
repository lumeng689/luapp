package org.luapp.practise.proto;

/**
 * 分块传输编码（Chunked transfer encoding）是超文本传输协议（HTTP）中的一种数据传输机制，允许HTTP由网页服务器发送给客户端应用（ 通常是网页浏览器）的数据可以分成多个部分。分块传输编码只在HTTP协议1.1版本（HTTP/1.1）中提供。
 * 通常，HTTP应答消息中发送的数据是整个发送的，Content-Length消息头字段表示数据的长度。
 * 数据的长度很重要，因为客户端需要知道哪里是应答消息的结束，以及后续应答消息的开始。
 * 然而，使用分块传输编码，数据分解成一系列数据块，并以一个或多个块发送，这样服务器可以发送
 * 数据而不需要预先知道发送内容的总大小。通常数据块的大小是一致的，但也不总是这种情况。
 *
 * <url>http://zh.wikipedia.org/wiki/%E5%88%86%E5%9D%97%E4%BC%A0%E8%BE%93%E7%BC%96%E7%A0%81</url>
 */
public class HttpTrunkHeaderTest {

    /**
    HTTP/1.1 200 OK
    Content-Type: text/plain
    Transfer-Encoding: chunked

    25
    This is the data in the first chunk

    1C
    and this is the second one

    3
    con

    8
    sequence

    0
     "This is the data in the first chunk\r\n"      (37 字符 => 十六进制: 0x25)
     "and this is the second one\r\n"               (28 字符 => 十六进制: 0x1C)
     "con"                                          (3  字符 => 十六进制: 0x03)
     "sequence"                                     (8  字符 => 十六进制: 0x08)
     **/
    public static void main(String args[]) {
        StringBuilder sb = new StringBuilder();
        sb.append("HTTP/1.1 200 OK\r\n");
        sb.append("Content-Type: text/plain\r\n");
        sb.append("Transfer-Encoding: chunked\r\n\r\n");
        sb.append("25\r\n");
        sb.append("This is the data in the first chunk\r\n"); // (37 字符 => 十六进制: 0x25)
        // (conveniently consisting of ASCII characters only)
        sb.append("\r\n1C\r\n");
        sb.append("and this is the second one"); // (28 字符 => 十六进制: 0x1C)
        // (conveniently consisting of ASCII characters only)
        sb.append("\r\n3\r\n");
        sb.append("con"); // (3  字符 => 十六进制: 0x03)
        // (conveniently consisting of ASCII characters only)
        sb.append("\r\n8\r\n");
        sb.append("sequence"); // (8  字符 => 十六进制: 0x08)
        // (conveniently consisting of ASCII characters only)
        sb.append("\r\n0\r\n\r\n");

        System.out.print(sb.toString());
    }
}
