package ru.netology;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameStoreTest {
    GameStore store = new GameStore();

    @Test
    public void shouldAddGame() { // добавляем одну игру в каталог
        List<Game> expected = new ArrayList<>();

        store.publishGame("Skyrim", "RPG");
        expected.add(new Game("Skyrim", "RPG", store));

        assertEquals(expected, store.getGames());
    }

    @Test
    public void shouldAddTwoGames() { // добавляем две игры в каталог
        List<Game> expected = new ArrayList<>();

        store.publishGame("Skyrim", "RPG");
        store.publishGame("GTA", "Action");

        expected.add(new Game("Skyrim", "RPG", store));
        expected.add(new Game("GTA", "Action", store));

        assertEquals(expected, store.getGames());
    }

    @Test
    public void shouldAddGameIfExists() { // добавляем игру в котолог, в котором уже есть данная игра
        List<Game> expected = new ArrayList<>();

        store.publishGame("Skyrim", "RGP");

        assertThrows(RuntimeException.class, () -> {
            store.publishGame("Skyrim", "RGP");
        });
    }

    //   Тесты на containsGame

    @Test
    public void shouldNotFindGame() {
        assertFalse(store.containsGame(new Game("GTA", "Action", store)));
    }

    @Test
    public void shouldFindGameIfOnlyOneGame() {
        store.publishGame("GTA", "Action");

        assertTrue(store.containsGame(new Game("GTA", "Action", store)));
    }

    @Test
    public void shouldNotFindGameIfOnlyOneAnotherGame() {
        store.publishGame("GTA", "Action");

        assertFalse(store.containsGame(new Game("Minecraft", "Action", store)));
    }

    @Test
    public void shouldFindGameIfSomeGames() {
        store.publishGame("GTA", "Action");
        store.publishGame("Skyrim", "RPG");
        store.publishGame("Minecraft", "Action");

        assertTrue(store.containsGame(new Game("GTA", "Action", store)));
    }

    //    Тесты addPlayTime

    @Test
    public void shouldAddPlayTime() {
        Map<String, Integer> expected = new HashMap<>();

        store.addPlayTime("Levon", 10);
        expected.put("Levon", 10);

        assertEquals(expected, store.getPlayedTime());
    }

    @Test
    public void shouldAddPlayTimeTwoPlayers() {
        Map<String, Integer> expected = new HashMap<>();

        store.addPlayTime("Levon", 50);
        store.addPlayTime("Vlad", 30);

        expected.put("Levon", 50);
        expected.put("Vlad", 30);

        assertEquals(expected, store.getPlayedTime());
    }

    @Test
    public void shouldAddPlayTimeOldPlayer() {
        Map<String, Integer> expected = new HashMap<>();

        store.addPlayTime("Levon", 40);
        store.addPlayTime("Levon", 10);

        expected.put("Levon", 50);

        assertEquals(expected, store.getPlayedTime());
    }

    @Test
    public void shouldAddPlayTimeExceptionIfNegativeHours() {
        assertThrows(RuntimeException.class, () -> {
            store.addPlayTime("Levon", -5);
        });
    }

    @Test
    public void shouldAddPlayTimeExceptionIfZeroHours() {
        assertThrows(RuntimeException.class, () -> {
            store.addPlayTime("Levon", 0);
        });
    }

    //  Тесты getMostPlayer

    @Test
    public void shouldGetMostPlayerIfOnePlayer() {
        store.addPlayTime("Levon", 10);

        assertEquals("Levon", store.getMostPlayer());
    }

    @Test
    public void shouldGetMostPlayerIfManyPlayers() {
        store.addPlayTime("Levon", 15);
        store.addPlayTime("Vlad", 30);
        store.addPlayTime("Ilya", 20);

        assertEquals("Vlad", store.getMostPlayer());
    }

    @Test
    public void shouldGetMostPlayerReturnNullIfNoPlayers() {
        assertEquals(null, store.getMostPlayer());
    }

    @Test
    public void shouldGetMostPlayerIfHoursEquallyOne() {
        store.addPlayTime("Levon", 1);

        assertEquals("Levon", store.getMostPlayer());
    }

    // Тесты getSumPlayedTime

    @Test
    public void shouldGetSumPlayedTimeIfNoPlayers() {

        int expected = 0;

        assertEquals(expected, store.getSumPlayedTime());
    }

    @Test
    public void shouldGetSumPlayedTimeIfOnePlayer() {

        store.addPlayTime("Levon", 45);

        int expected = 45;

        assertEquals(expected, store.getSumPlayedTime());
    }

    @Test
    public void shouldGetSumPlayedTimeIfManyPlayers() {

        store.addPlayTime("Levon", 20);
        store.addPlayTime("Vlad", 30);
        store.addPlayTime("Ilya", 25);
        store.addPlayTime("Olga", 30);

        int expected = 105;

        assertEquals(expected, store.getSumPlayedTime());
    }

}
