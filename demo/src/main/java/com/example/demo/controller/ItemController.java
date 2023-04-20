package com.example.demo.controller;

import com.example.demo.entity.Item;
import com.example.demo.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class ItemController {

    @Autowired
    private ItemService itemService;

    @GetMapping("/")
    public String getAll(Model model) {
        List<Item> itemList = itemService.getAll();
        model.addAttribute("itemList", itemList);
        model.addAttribute("itemSize", itemList.size());
        return "index";
    }

    @RequestMapping("/delete/{id}")
    public String deleteItem(@PathVariable int id) {
        itemService.delete(id);
        return "redirect:/";
    }

    @PostMapping("/add")
    public String addItem(@ModelAttribute Item item) {
        itemService.save(item);
        return "redirect:/";
    }

    @PostMapping("/search")
    public String searchItem(Model model, int id) {
        Optional<Item> item = itemService.searchById(id);
        model.addAttribute("searchItem", item);
        return "redirect:/";
    }
}