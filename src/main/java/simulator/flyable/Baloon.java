package simulator.flyable;

import simulator.tower.WeatherTower;

import java.io.IOException;

public class Baloon extends Aircraft implements Flyable {

    private WeatherTower weatherTower;

    Baloon(String name, Coordinates coordinates) {
        super(name, coordinates);
        this.behavior = new int[][]{{2, 0, 4}, {0, 0, -5}, {0, 0, -3}, {0, 0, -15}};
        this.messages = new String[][]
                {
                        {
                                "☀️ Вхожу в солнечную область. Скорей бы дождь...",
                                "☀️️ Шар - башне: Тут солнечно! Шары натерты кремом!",
                                "☀️️ Выключил горелку, но шар все еще поднимается! Запрашиваю ремонтную бригаду.",
                                "☀️️ Хочу позагорать, но мне шар солнце закрывает!",
                                "☀️️ Шар летит сам по себе! Таки экономия!"
                        },
                        {
                                "☂️️ Вхожу в дождивую область. Скорее б дождь кончился...",
                                "☂️️ Шар - башне: Пошел дождь. Мы промокли, нас тянет вниз.",
                                "☂️️ Не выключаю горелку, но одежда все еще мокрая! Запрашиваю полотенчик!",
                                "☂️️ Хочу помыться, но шар загораживает!",
                                "☂️️ Шар тратит слишком много газа! Таки не поездка, а сплошные затраты!"
                        },
                        {
                                "\uD83C\uDF2B️ Вхожу в туман! Когда же он наконец рассеится..?",
                                "\uD83C\uDF2B️ Шар - башне: Тут как-то туманно. Шар не видно!",
                                "\uD83C\uDF2B️ Горелка все время тухнет. Запрашиваю коробку спичек!",
                                "\uD83C\uDF2B️ Хочу сыграть в прятки, но меня не пускают...",
                                "\uD83C\uDF2B️ Таки наконец-то я не вижу своих попутчиков!"
                        },
                        {
                                "❄️ Пошел снег! Уже замерз, когда снова тепло?",
                                "❄️ Шар - башне: мы попали в метель! Идем на снижение.",
                                "❄️ Горелка замерзает, и мы вместе с ней! Запрашиваю какао и пледик.",
                                "❄️ Хочу слепить снеговика, но снег слишком рыхлый!",
                                "❄️ Ну шо за дела - пошел снег! Таки кто заплатит мне за новый шар?"
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
        return "Baloon";
    }
}
