package pub.wii.cook.springboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;

@Service
@Configurable
public class PingEchoService {

    @Autowired
    EchoService echoService;

    @Autowired
    PingService pingService;

    public String pe(String s) {
        return echoService.echo(s + " " + pingService.ping());
    }
}
