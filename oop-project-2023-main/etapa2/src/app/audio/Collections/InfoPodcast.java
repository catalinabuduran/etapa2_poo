package app.audio.Collections;

import java.util.List;

public class InfoPodcast {
    private String name;
    private List<String> episodesName;

    public InfoPodcast(final String name, final List<String> episodes) {
        this.name = name;
        this.episodesName = episodes;
    }

    public String getName() {
        return name;
    }
    public List<String> getEpisodes() {
        return episodesName;
    }
}
