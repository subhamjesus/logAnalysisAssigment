package com.creditsuisse.assignment;

import com.creditsuisse.assignment.entity.EventEntity;
import com.creditsuisse.assignment.repository.EventRepository;
import com.creditsuisse.assignment.service.impl.FileReaderServiceImpl;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.Mockito.any;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FileReaderServiceTest {
    @InjectMocks
    private FileReaderServiceImpl fileReaderService;

    @Mock
    private EventRepository eventRepository;


    @Test
    public void readFileTest() {
        EventEntity eventEntity = new EventEntity();
        eventEntity.setDuration(4l);
        eventEntity.setId("scsmbstgra");
        eventEntity.setHost("12345");
        eventEntity.setType("APPLICATION_LOG");
        Mockito.when(eventRepository.save(any(EventEntity.class))).thenReturn(eventEntity);

        String result = fileReaderService.readFile("logfile.txt");

        Assert.assertEquals("Success", result);
    }

    @Test
    public void parseJsonLogFileTest() {
        EventEntity eventEntity = new EventEntity();
        eventEntity.setDuration(4l);
        eventEntity.setId("scsmbstgra");
        eventEntity.setHost("12345");
        eventEntity.setType("APPLICATION_LOG");
        Mockito.when(eventRepository.save(any(EventEntity.class))).thenReturn(eventEntity);
        String result = fileReaderService.parseJsonLogFile("logfile.txt");
        Assert.assertEquals("Success", result);
    }

    @Test()
    public void parseJsonLogFileBlankLineTest() {
        EventEntity eventEntity = new EventEntity();
        eventEntity.setDuration(4l);
        eventEntity.setId("scsmbstgra");
        eventEntity.setHost("12345");
        eventEntity.setType("APPLICATION_LOG");
        Mockito.when(eventRepository.save(any(EventEntity.class))).thenReturn(eventEntity);
        String result = fileReaderService.parseJsonLogFile("logfile1.txt");
        Assert.assertEquals("Success", result);
    }

    @Test()
    public void parseJsonLogNumberFormatTest() {
        EventEntity eventEntity = new EventEntity();
        eventEntity.setDuration(4l);
        eventEntity.setId("scsmbstgra");
        eventEntity.setHost("12345");
        eventEntity.setType("APPLICATION_LOG");
        Mockito.when(eventRepository.save(any(EventEntity.class))).thenReturn(eventEntity);
        String result = fileReaderService.parseJsonLogFile("logfile2.txt");
        Assert.assertEquals("Failed", result);
    }

    @Test()
    public void parseJsonLogNoFileTest() {
        EventEntity eventEntity = new EventEntity();
        eventEntity.setDuration(4l);
        eventEntity.setId("scsmbstgra");
        eventEntity.setHost("12345");
        eventEntity.setType("APPLICATION_LOG");
        Mockito.when(eventRepository.save(any(EventEntity.class))).thenReturn(eventEntity);
        String result = fileReaderService.parseJsonLogFile("");
        Assert.assertEquals("Failed", result);
    }
}
