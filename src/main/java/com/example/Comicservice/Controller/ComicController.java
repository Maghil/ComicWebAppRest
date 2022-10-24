package com.example.Comicservice.Controller;

import com.example.Comicservice.service.ComicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/getComic")
public class ComicController {

    private final ComicService comicService;

    @Autowired
    ComicController(ComicService comicService) {
        this.comicService = comicService;
    }

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, String> latestComic() {
        HashMap<String, String> map = new HashMap<>();
        String comicId = comicService.getComic();
        map.put("comic", comicId);
        return map;
    }

    @GetMapping(value = "/next/{ComicId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, String> getNextComic(@PathVariable(value = "comicId") String comicId) {
        HashMap<String, String> map = new HashMap<>();
        String resultComicId = comicService.getNextComic(comicId);
        map.put("comic", resultComicId);
        return map;
    }

    @GetMapping(value = "/prev/{ComicId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, String> getPreviousComic(@PathVariable(value = "comicId") String comicId) {
        HashMap<String, String> map = new HashMap<>();
        String resultComicId = comicService.getPrevioussComic(comicId);
        map.put("comic", resultComicId);
        return map;
    }
}
