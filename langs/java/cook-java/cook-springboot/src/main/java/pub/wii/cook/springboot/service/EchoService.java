package pub.wii.cook.springboot.service;

import org.springframework.stereotype.Service;

@Service
public class EchoService {
    public String echo(String s) {
        return s;
    }
}
