namespace java org.luapp.core.thrift.service

service EchoService {
    string echo(1:string message);
}