package simulator.flyable;

import simulator.tower.WeatherTower;

import java.io.IOException;

public class JetPlane extends Aircraft implements Flyable {

    private WeatherTower weatherTower;

    JetPlane(String name, Coordinates coordinates) {
        super(name, coordinates);
        this.behavior = new int[][]{{0, 10, 2}, {0, 5, 0}, {0, 1, 0}, {0, 0, -7}};
        this.messages = new String[][]
                {
                        {
                                "☀️ Докладываю: вхожу в солнечную зону.",
                                "☀️ Истребитель - башне: Тут солнечно! Немного слепит.",
                                "☀️ Пролетаю красивые виды. Хорошая погода!",
                                "☀️ Очень жарко, открыл форточку. Шутка.",
                                "☀️ Вижу небесный купол! Я же не улечу в космос..? *помехи*"
                        },
                        {
                                "☂️ Докладываю: вхожу в зону дождя.",
                                "☂️ Истребитель - башне: Пошел дождь. Снижаю скорость.",
                                "☂️ Пролетаю красивые виды. Погодка не очень.",
                                "☂️ Очень сыро, закрыл форточку, но это не помогает. Жду инструкций.",
                                "☂️ Вижу неизвестную местность. Куда вы меня отправили..? *помехи*"
                        },
                        {
                                "\uD83C\uDF2B️ Докладываю: вхожу в туман.",
                                "\uD83C\uDF2B️ Истребитель - башне: Туман, плохая видимость.",
                                "\uD83C\uDF2B️ Пролетаю красивые виды. Вроде бы... Не уверен...",
                                "\uD83C\uDF2B️ В кабину закрался туман, открыл бы форточку, но не вижу ее.",
                                "\uD83C\uDF2B️ Ничего не вижу. Иду по приборам. *помехи*"
                        },
                        {
                                "❄️ Докладываю: пошел снег.",
                                "❄️ Истребитель - башне: Пошел снег. Оледенение заставляет снижаться.",
                                "❄️ Пролетаю красивые виды. Внизу красиво блестит снег",
                                "❄️ Холодно. Пора закрыть форточку...",
                                "❄️ Вижу землю! Передай моей жене, чт.. *помехи*"
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
        return "JetPlane";
    }

}
