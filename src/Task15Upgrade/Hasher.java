package Task15Upgrade;

//данный интерфейс внесет гибкость в хэширование ключей
public interface Hasher {
    int getHash (String key);
}
