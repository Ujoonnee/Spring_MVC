package hello.itemservice.domain.item;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class ItemRepositoryTest {

    ItemRepository itemRepository = new ItemRepository();

    @AfterEach
    void afterEach() {
        itemRepository.clearStore();
    }

    @Test
    void save() {
        //given
        Item item = new Item("itemA", 1000, 10);

        //when
        itemRepository.save(item);

        //then
        Item findItem = itemRepository.findById(item.getId());
        assertThat(findItem).isEqualTo(item);
    }

    @Test
    void findAll() {
        //given
        Item item1 = new Item("itemA", 1000, 10);
        Item item2 = new Item("itemA", 1000, 10);

        itemRepository.save(item1);
        itemRepository.save(item2);

        //when
        List<Item> itemList = itemRepository.findAll();

        //then
        assertThat(itemList.size()).isEqualTo(2);
        assertThat(itemList).contains(item1, item2);
    }

    @Test
    void update() {
        //given
        Item item = new Item("itemA", 1000, 10);

        Item savedItem = itemRepository.save(item);
        Long itemId = savedItem.getId();

        //when
        Item updatedParam = new Item("itemB", 2000, 20);
        itemRepository.update(item.getId(), updatedParam);

        //then
        Item findItem = itemRepository.findById(itemId);

        assertThat(findItem.getItemName()).isEqualTo(updatedParam.getItemName());
        assertThat(findItem.getPrice()).isEqualTo(updatedParam.getPrice());
        assertThat(findItem.getQuantity()).isEqualTo(updatedParam.getQuantity());
    }

}