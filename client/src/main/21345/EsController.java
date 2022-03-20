package com.shangjian.indicator.aestest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EsController {

    @Autowired
    private ItemRepository itemRepository;

//    @Autowired
//    private ItemRepositoryService itemRepositoryService;




    @GetMapping("es")
    public void es() {
//        itemRepositoryService.createIndex();
        Item item = new Item();
        item.setId(1234567890);
        item.setBrand("大众");
        item.setCategory("上汽");
        item.setPrice(13.9);
        item.setTitle("促销大减价");
        item.setCreateTime(System.currentTimeMillis());
        item.setImages("http://127.0.0.1:8080/images");
        item.setSearchWord("Boolean.TRUE");
        itemRepository.save(item);
//        itemRepositoryService.deleteIndex();


    }

}
