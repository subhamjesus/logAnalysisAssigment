package com.creditsuisse.assignment.service.impl;

import com.creditsuisse.assignment.entity.EventEntity;
import com.creditsuisse.assignment.model.Event;
import com.creditsuisse.assignment.repository.EventRepository;
import com.creditsuisse.assignment.service.FileReaderService;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.stream.Stream;

@Service
@Transactional
@Slf4j
public class FileReaderServiceImpl implements FileReaderService {

    Logger logger = LoggerFactory.getLogger(FileReaderServiceImpl.class);

    @Autowired
    private EventRepository eventRepository;

    /***
     * This method is used parse the json file
     * @param path
     * @return success or failed
     * @throws IllegalArgumentException
     */
    public String readFile(String path) throws IllegalArgumentException {
        if (path!=null && !path.isEmpty() ) {
            logger.info("Event execution Started");
            return parseJsonLogFile(path);
        }else {
            logger.error("File is not available");
            throw new IllegalArgumentException(" At-least 1 argument is required for execution.");
        }

    }

    /***
     * This method parse the file and check fore the time taken and save the data into database
     * @param path
     * @ success or failed
     */
    public String parseJsonLogFile(String path)  {

        String result = "Success";
        HashMap<String, Event> eventHashMap = new HashMap<String, Event>();

        logger.info("Json file parsing started");

        try (Stream<String> stream = Files.lines( Paths.get(path) ) ) {

            stream.map(eventJsonObject -> new Gson().fromJson(eventJsonObject, Event.class)).forEach(event -> {
                if (event == null) {
                    logger.error("Blank space or empty line found");
                    return;
                }
                if (eventHashMap.get(event.getId()) == null) {
                    eventHashMap.put(event.getId(), event);

                } else {
                    logger.debug("Calculating the duration ");
                    Long timeTaken = Math.abs((eventHashMap.get(event.id).getTimestamp() - event.getTimestamp()));
                     saveData(event, timeTaken);
                }
            });

        } catch (IOException ex) {
           logger.error("Error occurred while reading the file");
            result = "Failed";

        }
        catch (Exception exc) {
            logger.error("Error occurred while processing file",exc.getMessage());
            result = "Failed";
        }
        logger.info("Event execution completed and result is ", result);
        return result;
    }

    /***
     * IT saves the data into hsqldb
     * @param event
     * @param timeTaken
     */
    private void saveData(Event event,Long timeTaken){

        EventEntity eventEntity  = new EventEntity();
        eventEntity.setDuration(timeTaken);
        eventEntity.setId(event.id);
        eventEntity.setHost(event.host);
        eventEntity.setType(event.type);
        if (timeTaken>4){
            logger.debug("Setting alert to true");
            eventEntity.setAlert(true);
        } else {
            eventEntity.setAlert(false);
        }
        logger.debug("Saving data to database");
        eventRepository.save(eventEntity);
        logger.info("Data saved to database");
    }
}
