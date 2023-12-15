package app;

import app.audio.Collections.infoCollection.InfoPodcast;
import app.audio.Collections.PlaylistOutput;
import app.player.PlayerStats;
import app.searchBar.Filters;
import app.user.Artist;
import app.audio.Collections.infoCollection.InfoAlbum;
import app.user.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import fileio.input.CommandInput;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Command runner.
 */
public final class CommandRunner {
    /**
     * The Object mapper.
     */
    private static ObjectMapper objectMapper = new ObjectMapper();

    private CommandRunner() {
    }

    /**
     * Search object node.
     *
     * @param commandInput the command input
     * @return the object node
     */
    public static ObjectNode search(final CommandInput commandInput) {
        Admin admin = Admin.getInstance();
        User user = admin.getUser(commandInput.getUsername());
        Filters filters = new Filters(commandInput.getFilters());
        String type = commandInput.getType();
        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());

        ArrayList<String> results = user.search(filters, type);
        if (user.isOnline()) {
            String message = "Search returned " + results.size() + " results";
            objectNode.put("message", message);
            objectNode.put("results", objectMapper.valueToTree(results));
        } else {
            String errorMessage = String.format("%s is offline.", user.getUsername());
            objectNode.put("message", errorMessage);
            objectNode.put("results", objectMapper.valueToTree(new ArrayList<>()));
        }


        return objectNode;
    }

    /**
     * Select object node.
     *
     * @param commandInput the command input
     * @return the object node
     */
    public static ObjectNode select(final CommandInput commandInput) {
        Admin admin = Admin.getInstance();
        User user = admin.getUser(commandInput.getUsername());

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());

        if (user.isOnline()) {
            String message = user.select(commandInput.getItemNumber(), commandInput.getUsername());
            objectNode.put("message", message);
        } else {
            String errorMessage = String.format("%s is offline.", user.getUsername());
            objectNode.put("message", errorMessage);
        }
        return objectNode;
    }

    /**
     * Load object node.
     *
     * @param commandInput the command input
     * @return the object node
     */
    public static ObjectNode load(final CommandInput commandInput) {
        Admin admin = Admin.getInstance();
        User user = admin.getUser(commandInput.getUsername());

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());
        if (user.isOnline()) {
            String message = user.load();
            objectNode.put("message", message);
        } else {
            String errorMessage = String.format("%s is offline.", user.getUsername());
            objectNode.put("message", errorMessage);
        }

        return objectNode;
    }

    /**
     * Play pause object node.
     *
     * @param commandInput the command input
     * @return the object node
     */
    public static ObjectNode playPause(final CommandInput commandInput) {
        Admin admin = Admin.getInstance();
        User user = admin.getUser(commandInput.getUsername());

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());

        if (user.isOnline()) {
            String message = user.playPause();
            objectNode.put("message", message);
        } else {
            String errorMessage = String.format("%s is offline.", user.getUsername());
            objectNode.put("message", errorMessage);
        }

        return objectNode;
    }

    /**
     * Repeat object node.
     *
     * @param commandInput the command input
     * @return the object node
     */
    public static ObjectNode repeat(final CommandInput commandInput) {
        Admin admin = Admin.getInstance();
        User user = admin.getUser(commandInput.getUsername());

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());
        if (user.isOnline()) {
            String message = user.repeat();
            objectNode.put("message", message);
        } else {
            String errorMessage = String.format("%s is offline.", user.getUsername());
            objectNode.put("message", errorMessage);
        }

        return objectNode;
    }

    /**
     * Shuffle object node.
     *
     * @param commandInput the command input
     * @return the object node
     */
    public static ObjectNode shuffle(final CommandInput commandInput) {
        Admin admin = Admin.getInstance();
        User user = admin.getUser(commandInput.getUsername());
        Integer seed = commandInput.getSeed();

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());
        if (user.isOnline()) {
            String message = user.shuffle(seed);
            objectNode.put("message", message);
        } else {
            String errorMessage = String.format("%s is offline.", user.getUsername());
            objectNode.put("message", errorMessage);
        }
        return objectNode;
    }

    /**
     * Forward object node.
     *
     * @param commandInput the command input
     * @return the object node
     */
    public static ObjectNode forward(final CommandInput commandInput) {
        Admin admin = Admin.getInstance();
        User user = admin.getUser(commandInput.getUsername());

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());
        if (user.isOnline()) {
            String message = user.forward();
            objectNode.put("message", message);
        } else {
            String errorMessage = String.format("%s is offline.", user.getUsername());
            objectNode.put("message", errorMessage);
        }

        return objectNode;
    }

    /**
     * Backward object node.
     *
     * @param commandInput the command input
     * @return the object node
     */
    public static ObjectNode backward(final CommandInput commandInput) {
        Admin admin = Admin.getInstance();
        User user = admin.getUser(commandInput.getUsername());

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());
        if (user.isOnline()) {
            String message = user.backward();
            objectNode.put("message", message);
        } else {
            String errorMessage = String.format("%s is offline.", user.getUsername());
            objectNode.put("message", errorMessage);
        }
        return objectNode;
    }

    /**
     * Like object node.
     *
     * @param commandInput the command input
     * @return the object node
     */
    public static ObjectNode like(final CommandInput commandInput) {
        Admin admin = Admin.getInstance();
        User user = admin.getUser(commandInput.getUsername());

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());
        if (user.isOnline()) {
            String message = user.like();
            objectNode.put("message", message);
        } else {
            String errorMessage = String.format("%s is offline.", user.getUsername());
            objectNode.put("message", errorMessage);
        }

        return objectNode;
    }

    /**
     * Next object node.
     *
     * @param commandInput the command input
     * @return the object node
     */
    public static ObjectNode next(final CommandInput commandInput) {
        Admin admin = Admin.getInstance();
        User user = admin.getUser(commandInput.getUsername());

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());
        if (user.isOnline()) {
            String message = user.next();
            objectNode.put("message", message);
        } else {
            String errorMessage = String.format("%s is offline.", user.getUsername());
            objectNode.put("message", errorMessage);
        }
        return objectNode;
    }

    /**
     * Prev object node.
     *
     * @param commandInput the command input
     * @return the object node
     */
    public static ObjectNode prev(final CommandInput commandInput) {
        Admin admin = Admin.getInstance();
        User user = admin.getUser(commandInput.getUsername());

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());
        if (user.isOnline()) {
            String message = user.prev();
            objectNode.put("message", message);
        } else {
            String errorMessage = String.format("%s is offline.", user.getUsername());
            objectNode.put("message", errorMessage);
        }
        return objectNode;
    }

    /**
     * Create playlist object node.
     *
     * @param commandInput the command input
     * @return the object node
     */
    public static ObjectNode createPlaylist(final CommandInput commandInput) {
        Admin admin = Admin.getInstance();
        User user = admin.getUser(commandInput.getUsername());

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());
        if (user.isOnline()) {
            String message = user.createPlaylist(commandInput.getPlaylistName(),
                    commandInput.getTimestamp());
            objectNode.put("message", message);
        } else {
            String errorMessage = String.format("%s is offline.", user.getUsername());
            objectNode.put("message", errorMessage);
        }
        return objectNode;
    }

    /**
     * Add remove in playlist object node.
     *
     * @param commandInput the command input
     * @return the object node
     */
    public static ObjectNode addRemoveInPlaylist(final CommandInput commandInput) {
        Admin admin = Admin.getInstance();
        User user = admin.getUser(commandInput.getUsername());

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());
        if (user.isOnline()) {
            String message = user.addRemoveInPlaylist(commandInput.getPlaylistId());
            objectNode.put("message", message);
        } else {
            String errorMessage = String.format("%s is offline.", user.getUsername());
            objectNode.put("message", errorMessage);
        }

        return objectNode;
    }

    /**
     * Switch visibility object node.
     *
     * @param commandInput the command input
     * @return the object node
     */
    public static ObjectNode switchVisibility(final CommandInput commandInput) {
        Admin admin = Admin.getInstance();
        User user = admin.getUser(commandInput.getUsername());

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());
        if (user.isOnline()) {
            String message = user.switchPlaylistVisibility(commandInput.getPlaylistId());
            objectNode.put("message", message);
        } else {
            String errorMessage = String.format("%s is offline.", user.getUsername());
            objectNode.put("message", errorMessage);
        }
        return objectNode;
    }

    /**
     * Show playlists object node.
     *
     * @param commandInput the command input
     * @return the object node
     */
    public static ObjectNode showPlaylists(final CommandInput commandInput) {
        Admin admin = Admin.getInstance();
        User user = admin.getUser(commandInput.getUsername());
        ArrayList<PlaylistOutput> playlists = user.showPlaylists();

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("result", objectMapper.valueToTree(playlists));

        return objectNode;
    }

    /**
     * Follow object node.
     *
     * @param commandInput the command input
     * @return the object node
     */
    public static ObjectNode follow(final CommandInput commandInput) {
        Admin admin = Admin.getInstance();
        User user = admin.getUser(commandInput.getUsername());

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());
        if (user.isOnline()) {
            String message = user.follow();
            objectNode.put("message", message);
        } else {
            String errorMessage = String.format("%s is offline.", user.getUsername());
            objectNode.put("message", errorMessage);
        }

        return objectNode;
    }

    /**
     * Status object node.
     *
     * @param commandInput the command input
     * @return the object node
     */
    public static ObjectNode status(final CommandInput commandInput) {
        Admin admin = Admin.getInstance();
        User user = admin.getUser(commandInput.getUsername());
        PlayerStats stats = user.getPlayerStats();

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("stats", objectMapper.valueToTree(stats));

        return objectNode;
    }

    /**
     * Show liked songs object node.
     *
     * @param commandInput the command input
     * @return the object node
     */
    public static ObjectNode showLikedSongs(final CommandInput commandInput) {
        Admin admin = Admin.getInstance();
        User user = admin.getUser(commandInput.getUsername());
        ArrayList<String> songs = user.showPreferredSongs();

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("result", objectMapper.valueToTree(songs));

        return objectNode;
    }

    /**
     * Gets preferred genre.
     *
     * @param commandInput the command input
     * @return the preferred genre
     */
    public static ObjectNode getPreferredGenre(final CommandInput commandInput) {
        Admin admin = Admin.getInstance();
        User user = admin.getUser(commandInput.getUsername());
        String preferredGenre = user.getPreferredGenre();

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("result", objectMapper.valueToTree(preferredGenre));

        return objectNode;
    }

    /**
     * Gets top 5 songs.
     *
     * @param commandInput the command input
     * @return the top 5 songs
     */
    public static ObjectNode getTop5Songs(final CommandInput commandInput) {
        Admin admin = Admin.getInstance();
        List<String> songs = admin.getTop5Songs();

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("result", objectMapper.valueToTree(songs));

        return objectNode;
    }

    /**
     * Gets top 5 playlists.
     *
     * @param commandInput the command input
     * @return the top 5 playlists
     */
    public static ObjectNode getTop5Playlists(final CommandInput commandInput) {
        Admin admin = Admin.getInstance();
        List<String> playlists = admin.getTop5Playlists();

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("result", objectMapper.valueToTree(playlists));

        return objectNode;
    }

    /**
     * Switch connections status
     * @param commandInput
     * @return
     */
    public static ObjectNode switchConnectionStatus(final CommandInput commandInput) {
        Admin admin = Admin.getInstance();
        User user = admin.getUser(commandInput.getUsername());

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());

        if (user == null) {
            String errorMessage = "The username " + commandInput.getUsername() + " doesn't exist.";
            objectNode.put("message", errorMessage);
            return objectNode;
        } else {
            String message = user.switchConnectionStatus(commandInput.getUsername());
            objectNode.put("message", message);
            return objectNode;
        }
    }

    /**
     * Get online users
     *
     * @param commandInput
     * @return
     */
    public static ObjectNode getOnlineUsers(final CommandInput commandInput) {
        Admin admin = Admin.getInstance();
        List<String> onlineUsersList = admin.onlineUsers();

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("result", objectMapper.valueToTree(onlineUsersList));
        return objectNode;
    }

    /**
     * Add user
     * @param commandInput
     * @return
     */
    public static ObjectNode addUser(final CommandInput commandInput) {
        Admin admin = Admin.getInstance();
        User user = admin.getUser(commandInput.getUsername());
        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());
        String message = user.addUser(commandInput.getUsername(),
                commandInput.getAge(), commandInput.getCity(),
                commandInput.getType());
        objectNode.put("message", message);
        return objectNode;
    }

    /**
     * Add album
     *
     * @param commandInput
     * @return
     */
    public static ObjectNode addAlbum(final CommandInput commandInput) {
        String message = Artist.addAlbum(commandInput.getUsername(),
                commandInput.getName(), commandInput.getReleaseYear(),
                commandInput.getDescription(), commandInput.getSongs());
        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("message", message);
        return objectNode;
    }

    /**
     * Show albums
     *
     * @param commandInput
     * @return
     */
    public static ObjectNode showAlbums(final CommandInput commandInput) {
        Admin admin = Admin.getInstance();
        User user = admin.getUser(commandInput.getUsername());
        List<InfoAlbum> albums = admin.showAlbums(commandInput.getUsername());

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());

        if (albums != null) {
            List<ObjectNode> albumNodes = new ArrayList<>();
            for (InfoAlbum album : albums) {
                ObjectNode albumNode = objectMapper.createObjectNode();
                albumNode.put("name", album.getName());
                albumNode.put("songs", objectMapper.valueToTree(album.getSongs()));
                albumNodes.add(albumNode);
            }
            objectNode.put("result", objectMapper.valueToTree(albumNodes));
        } else {
            objectNode.putArray("result");
        }

        return objectNode;
    }

    /**
     * Add event
     *
     * @param commandInput
     * @return
     */
    public static ObjectNode addEvent(final CommandInput commandInput) {
        String message = User.addEvent(commandInput.getUsername(), commandInput.getName(),
                commandInput.getDescription(), commandInput.getDate());
        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("message", message);
        return objectNode;
    }

    /**
     * Add merch
     *
     * @param commandInput
     * @return
     */
    public static ObjectNode addMerch(final CommandInput commandInput) {
        String message = User.addMerch(commandInput.getUsername(),
                commandInput.getName(), commandInput.getDescription(),
                commandInput.getPrice());
        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("message", message);
        return objectNode;
    }

    /**
     * Print current page
     *
     * @param commandInput
     * @return
     */
    public static ObjectNode printCurrentPage(final CommandInput commandInput) {
        Admin admin = Admin.getInstance();
        String username = commandInput.getUsername();
        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("timestamp", commandInput.getTimestamp());

        User user = admin.getUser(username);

        if (user.isOnline()) {
            if (user == null) {
                String errorMessage = String.format("User with username %s not found.", username);
                objectNode.put("message", errorMessage);
            } else {
                objectNode.put("message", objectMapper.valueToTree(
                        user.printCurrentPage(username)));
            }
        } else {
            String errorMesage = commandInput.getUsername() + " is offline.";
            objectNode.put("message", errorMesage);
        }

        return objectNode;
    }

    /**
     * Get all users
     * @param commandInput
     * @return
     */
    public static ObjectNode getAllUsers(final CommandInput commandInput) {
        Admin admin = Admin.getInstance();
        List<String> allUsersList = admin.allUsers();

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("result", objectMapper.valueToTree(allUsersList));
        return objectNode;
    }

    /**
     * Delete user
     *
     * @param commandInput
     * @return
     */
    public static ObjectNode deleteUser(final CommandInput commandInput) {
        String message = User.deleteUser(commandInput.getUsername());
        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("message", message);
        return objectNode;
    }

    /**
     * Add podcast
     *
     * @param commandInput
     * @return
     */
    public static ObjectNode addPodcast(final CommandInput commandInput) {
        String message = User.addPodcast(commandInput.getUsername(),
                commandInput.getName(), commandInput.getTimestamp(),
                commandInput.getEpisodes());
        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("message", message);
        return objectNode;
    }

    /**
     * Add announcemenet
     *
     * @param commandInput
     * @return
     */
    public static ObjectNode addAnnouncement(final CommandInput commandInput) {
        String message = User.addAnnouncement(commandInput.getUsername(), commandInput.getName(),
                commandInput.getTimestamp(), commandInput.getDescription());
        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("message", message);
        return objectNode;
    }

    /**
     * Remove podcast
     *
     * @param commandInput
     * @return
     */
    public static ObjectNode removePodcast(final CommandInput commandInput) {
        String message = User.removePodcast(commandInput.getUsername(), commandInput.getName());
        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("message", message);
        return objectNode;
    }

    /**
     * Remove announcement
     *
     * @param commandInput
     * @return
     */
    public static ObjectNode removeAnnouncement(final CommandInput commandInput) {
        String message = User.removeAnnouncement(commandInput.getUsername(),
                commandInput.getName());
        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("message", message);
        return objectNode;
    }

    /**
     * Show podcasts
     *
     * @param commandInput
     * @return
     */
    public static ObjectNode showPodcasts(final CommandInput commandInput) {
        Admin admin = Admin.getInstance();
        User user = admin.getUser(commandInput.getUsername());
        List<InfoPodcast> podcasts = admin.showPodcasts(commandInput.getUsername());

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());

        if (podcasts != null) {
            List<ObjectNode> podcastNodes = new ArrayList<>();
            for (InfoPodcast podcast : podcasts) {
                ObjectNode albumNode = objectMapper.createObjectNode();
                albumNode.put("name", podcast.getName());
                albumNode.put("episodes", objectMapper.valueToTree(
                        podcast.getEpisodes()));
                podcastNodes.add(albumNode);
            }
            objectNode.put("result", objectMapper.valueToTree(podcastNodes));
        } else {
            objectNode.putArray("result");
        }
        return objectNode;
    }

    /**
     * Change page
     *
     * @param commandInput
     * @return
     */
    public static ObjectNode changePage(final CommandInput commandInput) {
        Admin admin = Admin.getInstance();
        User user = admin.getUser(commandInput.getUsername());

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());
        if (user.isOnline()) {
            String message = user.changePage(commandInput.getUsername(),
                    commandInput.getNextPage());
            objectNode.put("message", message);
        } else {
            String errorMessage = String.format("%s is offline.", user.getUsername());
            objectNode.put("message", errorMessage);
        }
        return objectNode;
    }

    /**
     * Remove album
     *
     * @param commandInput
     * @return
     */
    public static ObjectNode removeAlbum(final CommandInput commandInput) {
        String message = User.removeAlbum(commandInput.getUsername(),
                commandInput.getName());
        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("message", message);
        return objectNode;
    }

    /**
     * Get top 5 albums
     *
     * @param commandInput
     * @return
     */
    public static ObjectNode getTop5Albums(final CommandInput commandInput) {
        Admin admin = Admin.getInstance();
        List<String> albums = admin.getTop5Albums();

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("result", objectMapper.valueToTree(albums));

        return objectNode;
    }

    /**
     * Remove event
     *
     * @param commandInput
     * @return
     */
    public static ObjectNode removeEvent(final CommandInput commandInput) {
        String message = User.removeEvent(commandInput.getUsername(),
                commandInput.getName());
        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("message", message);
        return objectNode;
    }

    /**
     * Get top 5 artists
     *
     * @param commandInput
     * @return
     */
    public static ObjectNode getTop5Artists(final CommandInput commandInput) {
        Admin admin = Admin.getInstance();
        List<String> artists = admin.getTop5Artists();

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("result", objectMapper.valueToTree(artists));

        return objectNode;
    }
}
