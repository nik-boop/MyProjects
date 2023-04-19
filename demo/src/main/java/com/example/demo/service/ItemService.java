package com.example.demo.service;

import com.example.demo.entity.Item;
import com.example.demo.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    public List<Item> getAll() {
        return itemRepository.findAll(Sort.by(Sort.Order.asc("date"),
                Sort.Order.desc("id")));
    }

    public Item save(Item item) {
        return itemRepository.save(item);
    }

    public void delete(int id) {
        itemRepository.deleteById(id);
    }
    public Optional<Item> searchById(int id) {
        return itemRepository.findById(id);
    }
}