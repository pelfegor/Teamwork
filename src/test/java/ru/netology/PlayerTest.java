package ru.netology;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class PlayerTest {

    GameStore store = new GameStore();

    @Test
    public void shouldSumGenreIfOneGame() {  //Суммируем время за все игры жанра, если одна игра

        Game game = store.publishGame("Нетология Баттл Онлайн", "Аркады");

        Player player = new Player("Petya");
        player.installGame(game);
        player.play(game, 3);

        int expected = 3;
        int actual = player.sumGenre(game.getGenre());
        assertEquals(expected, actual);
    }

    @Test
    public void shouldSumGenreIfSomeGamesSameGenre() { //Суммируем время за все игры жанра, если несколько игр одного жанра

        Game game1 = store.publishGame("Нетология Баттл Онлайн", "Аркады");
        Game game2 = store.publishGame("Minecraft", "Аркады");
        Game game3 = store.publishGame("GTA", "Аркады");

        Player player = new Player("Petya");
        player.installGame(game1);
        player.installGame(game2);
        player.installGame(game3);
        player.play(game1, 3);
        player.play(game2, 4);
        player.play(game3, 5);

        int expected = 12;
        int actual = player.sumGenre("Аркады");
        assertEquals(expected, actual);
    }

    @Test
    public void shouldSumGenreIfSomeGamesDifferentGenre() { //Суммируем время за все игры жанра, если несколько игр разных жанров

        Game game1 = store.publishGame("Нетология Баттл Онлайн", "Аркады");
        Game game2 = store.publishGame("Minecraft", "RPG");
        Game game3 = store.publishGame("GTA", "Аркады");

        Player player = new Player("Petya");
        player.installGame(game1);
        player.installGame(game2);
        player.installGame(game3);
        player.play(game1, 3);
        player.play(game2, 4);
        player.play(game3, 5);

        int expected = 8;
        int actual = player.sumGenre("Аркады");
        assertEquals(expected, actual);
    }

    @Test
    public void shouldSumPlayHoursIfGameNotInstall() { //считаем кол-во часов за игру, если игра не установлена

        Game game = store.publishGame("Нетология Баттл Онлайн", "Аркады");

        Player player = new Player("Petya");

        assertThrows(RuntimeException.class, () -> player.play(game, 3));
    }

    @Test
    public void shouldMostPlayerByGenreIfPlayJustGameThisGenre() { //Проверяем топ жанра, если играли только в игру этого жанра

        Game game1 = store.publishGame("Нетология Баттл Онлайн", "Аркады");
        Game game2 = store.publishGame("Minecraft", "Аркады");
        Game game3 = store.publishGame("GTA", "Аркады");

        Player player = new Player("Petya");
        player.installGame(game1);
        player.installGame(game2);
        player.installGame(game3);
        player.play(game1, 3);
        player.play(game2, 4);
        player.play(game3, 5);

        Game expected = game3;
        Game actual = player.mostPlayerByGenre("Аркады");
        assertEquals(expected, actual);
    }

    @Test
    public void shouldMostPlayerByGenreIfPlayDifferentGenre() { //Проверяем топ жанра, если играли в игры разных жанров

        Game game1 = store.publishGame("Нетология Баттл Онлайн", "Аркады");
        Game game2 = store.publishGame("Minecraft", "Аркады");
        Game game3 = store.publishGame("GTA", "RPG");

        Player player = new Player("Petya");
        player.installGame(game1);
        player.installGame(game2);
        player.installGame(game3);
        player.play(game1, 3);
        player.play(game2, 4);
        player.play(game3, 5);

        Game expected = game2;
        Game actual = player.mostPlayerByGenre("Аркады");
        assertEquals(expected, actual);
    }

    @Test
    public void shouldMostPlayerByGenreIfDidNotPlayThisGenre() { //Проверяем, что null, если не играли в игру этого жанра

        Game game1 = store.publishGame("Нетология Баттл Онлайн", "Аркады");
        Game game2 = store.publishGame("Minecraft", "RPG");

        Player player = new Player("Petya");
        player.installGame(game1);
        player.installGame(game2);
        player.play(game1, 3);
        player.play(game2, 4);

        assertNull(player.mostPlayerByGenre("Гонки"));
    }

    @Test
    public void addPlayGameNegativeValue() { //Проверяем исключение, если задать отрицательное время

        Game game = store.publishGame("Нетология Баттл Онлайн", "Аркады");

        Player player = new Player("Petya");

        player.installGame(game);

        assertThrows(Exception.class, () -> player.play(game, -3));
    }

    @Test
    public void shouldInstallGameAgain() {

        Game game = store.publishGame("Нетология Баттл Онлайн", "Аркады");

        Player player = new Player("Petya");

        player.installGame(game);
        player.play(game, 3);
        player.installGame(game);

        int expected = 3;
        int actual = player.sumGenre("Аркады");

        assertEquals(expected, actual);
    }


    // другие ваши тесты
}
