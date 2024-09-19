package pub.wii.cook.springboot.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Controller;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import pub.wii.cook.springboot.model.Message;

import javax.annotation.Resource;

@Controller
@RequestMapping("kafka")
public class KafkaController {
    static class Keeper {
        String result;
    }

    @Resource
    KafkaTemplate<String, String> template;

    @RequestMapping(value = "put", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<String> check(@RequestBody Message message) throws InterruptedException {
        ListenableFuture<SendResult<String, String>> f = template.send("example", message.getKey(), message.getMessage());
        Object mutex = new Object();
        final Keeper keeper = new Keeper();
        keeper.result = "unknown";
        f.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
            @Override
            public void onFailure(@SuppressWarnings("NullableProblems") Throwable throwable) {
                System.out.println("ckpt; failure");
                keeper.result = "send message failed";
                synchronized (mutex) {
                    mutex.notify();
                }
            }

            @Override
            public void onSuccess(SendResult<String, String> stringStringSendResult) {
                keeper.result = "send message success";
                synchronized (mutex) {
                    mutex.notify();
                }
            }
        });

        synchronized (mutex) {
            mutex.wait();
        }

        return ResponseEntity.ok(keeper.result);
    }
}
