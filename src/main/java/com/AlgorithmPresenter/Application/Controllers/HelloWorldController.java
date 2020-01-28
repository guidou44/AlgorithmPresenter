package com.AlgorithmPresenter.Application.Controllers;

import org.springframework.http.MediaType;
import org.springframework.stereotype.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.time.LocalTime;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.springframework.http.MediaType.*;


@Controller
public class HelloWorldController extends ControllerBase {

    @GetMapping("/greeting")
    public String greeting(@RequestParam(name="name", required=false, defaultValue="World")
                                           String name, Model model) {
        model.addAttribute("name", name); //makes available for view
        return getMainViewName();
    }


    //region Sample SSE

    @GetMapping(path = "/comment/stream", produces = TEXT_EVENT_STREAM_VALUE)
    public ResponseBodyEmitter handleRequest () {

        final SseEmitter emitter = new SseEmitter();
        ExecutorService service = Executors.newSingleThreadExecutor();
        service.execute(() -> {
            for (int i = 0; i < 500; i++) {
                try {
                    emitter.send(LocalTime.now().toString());

                    Thread.sleep(200);
                } catch (Exception e) {
                    e.printStackTrace();
                    emitter.completeWithError(e);
                    return;
                }
            }
            emitter.complete();
        });

        return emitter;
    }

//    @GetMapping(value = "/notifyonEvent", produces =
//            MediaType.TEXT_EVENT_STREAM_VALUE)
//    public Flux<String> getData() {
//        Random r = new Random();
//        int low = 0;
//        int high = 50;
//        return Flux.fromStream(Stream.generate(() -> r.nextInt(high - low) + low)
//                .map(s -> String.valueOf(s))
//                .peek((msg) -> {
//                    LOGGER.info(msg);
//                }))
//                .map(s -> s)
//                .delayElements(Duration.ofSeconds(1));
//    }
//

    //endregion

}
