package com.workintech.s18d1.controller;

import com.workintech.s18d1.dao.BurgerDao;
import com.workintech.s18d1.entity.BreadType;
import com.workintech.s18d1.entity.Burger;
import com.workintech.s18d1.util.BurgerValidation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping({"/burger", "/workintech/burgers"})
public class BurgerController {

    private final BurgerDao burgerDao;

    public BurgerController(BurgerDao burgerDao) {
        this.burgerDao = burgerDao;
    }

    @GetMapping
    public List<Burger> findAll() {
        return execute("findAll", burgerDao::findAll);
    }

    @GetMapping("/{id}")
    public Burger findById(@PathVariable Long id) {
        BurgerValidation.validateId(id);
        return execute("findById", () -> burgerDao.findById(id));
    }

    @PostMapping
    public Burger save(@RequestBody Burger burger) {
        BurgerValidation.validateBurger(burger);
        return execute("save", () -> burgerDao.save(burger));
    }

    @PutMapping
    public Burger update(@RequestBody Burger burger) {
        BurgerValidation.validateId(burger.getId());
        return execute("update", () -> burgerDao.update(burger));
    }

    @PutMapping("/{id}")
    public Burger updateById(@PathVariable Long id, @RequestBody Burger burger) {
        burger.setId(id);
        return update(burger);
    }

    @DeleteMapping("/{id}")
    public Burger remove(@PathVariable Long id) {
        BurgerValidation.validateId(id);
        return execute("remove", () -> burgerDao.remove(id));
    }

    @GetMapping("/breadType/{breadType}")
    public List<Burger> findByBreadType(@PathVariable BreadType breadType) {
        return execute("findByBreadType", () -> burgerDao.findByBreadType(breadType));
    }

    @GetMapping("/price/{price}")
    public List<Burger> findByPrice(@PathVariable int price) {
        BurgerValidation.validatePrice(price);
        return execute("findByPrice", () -> burgerDao.findByPrice(price));
    }

    @GetMapping("/findByPrice/{price}")
    public List<Burger> findByPriceAlias(@PathVariable int price) {
        return findByPrice(price);
    }

    @GetMapping("/content/{content}")
    public List<Burger> findByContent(@PathVariable String content) {
        BurgerValidation.validateContent(content);
        return execute("findByContent", () -> burgerDao.findByContent(content));
    }

    @GetMapping("/findByContent/{content}")
    public List<Burger> findByContentAlias(@PathVariable String content) {
        return findByContent(content);
    }

    @GetMapping("/findByBreadType/{breadType}")
    public List<Burger> findByBreadTypeAlias(@PathVariable BreadType breadType) {
        return findByBreadType(breadType);
    }

    private <T> T execute(String operation, ControllerSupplier<T> supplier) {
        try {
            return supplier.get();
        } catch (RuntimeException exception) {
            log.error("Burger controller {} failed: {}", operation, exception.getMessage(), exception);
            throw exception;
        }
    }

    @FunctionalInterface
    private interface ControllerSupplier<T> {
        T get();
    }
}
