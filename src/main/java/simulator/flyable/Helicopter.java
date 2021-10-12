package simulator.flyable;

import simulator.tower.WeatherProvider;
import simulator.tower.WeatherTower;

import java.io.IOException;

public class Helicopter extends Aircraft implements Flyable {

    private WeatherTower weatherTower;

    Helicopter(String name, Coordinates coordinates) {
        super(name, coordinates);
        this.behavior = new int[][]{{10, 0, 2}, {5, 0, 0}, {1, 0, 0}, {0, 0, -12}};
        this.messages = new String[][]
                {
                        {
                                "☀️ Стоит жара. Чувствую себя в пустыне.",
                                "☀️ Вертолет - башне: Напомните взять солнцезащитный крем в следующий раз!",
                                "☀️ Запрашиваю разрешение приземлиться на пляже и купить мороженного.",
                                "☀️ Солнце меня радует!",
                                "☀️ Такое яркое солнце я не видел с лета 79го."
                        },
                        {
                                "☂️ Начался дождь. Чувствую себя в Питере.",
                                "☂️ Вертолет - башне: Поблизости есть громоотвод?",
                                "☂️ Запрашиваю разрешение приземлиться - нужно заменить дворник.",
                                "☂️ Дождик освежает!",
                                "☂️ Такой сильный дождь я помню по экспедиции в год олимпиады."
                        },
                        {
                                "\uD83C\uDF2B️ Начался туман. Чувствую себя как в тумане.",
                                "\uD83C\uDF2B️ Вертолет - башне: Кажется, мы потеряли кого-то, но я не уверен. Не вижу.",
                                "\uD83C\uDF2B️ Запрашиваю разрешение приземлиться - в такой туман пешком будет безопаснее.",
                                "\uD83C\uDF2B️ Туман дает время подумать!",
                                "\uD83C\uDF2B️ Такой густой туман я не видел с нашей высадки в Новую Англию."
                        },
                        {
                                "❄️ Начался снег. Чувствую себя хорошо.",
                                "❄️ Вертолет - башне: Вы знали, что можно вести вертолет и лепить снежки одновременно?",
                                "❄️ Запрашиваю разрешение приземлиться. И это никак не связано со снеговиками!",
                                "❄️ Снег наполняет сердце весельем!",
                                "❄️ Такой чистый снег я видел в Новый Год в детстве."
                        }
                };
    }

    @Override
    public void updateConditions() throws IOException {
        move(weatherTower);
    }

    @Override
    public void registerTower(WeatherTower weatherTower) {
        this.weatherTower = weatherTower;
    }

    @Override
    public String getType() {
        return "Helicopter";
    }

}
