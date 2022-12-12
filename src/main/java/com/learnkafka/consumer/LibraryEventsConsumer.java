package com.learnkafka.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class LibraryEventsConsumer {
    @Autowired
    private LibraryEventsService libraryEventsService;

    @KafkaListener(
            topics = {"library-events"}
            , autoStartup = "${libraryListener.startup:true}"
            , groupId = "library-events-listener-group")
    public void onMessage(ConsumerRecord<Integer, String> consumerRecord) throws JsonProcessingException {

        log.info("ConsumerRecord : {} ", consumerRecord);
        libraryEventsService.processLibraryEvent(consumerRecord);

    }
}
