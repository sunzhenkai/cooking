package pub.wii.cook.springboot.service;

import org.springframework.stereotype.Service;

@Service
public class PingService {
    public String ping() {
        return "pong";
    }
}
