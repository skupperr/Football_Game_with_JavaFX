package com.example.loginpage;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DatabaseHandler {

    private final String url = "jdbc:mysql://localhost:3307/football_game";
    private final String dbUsername = "root";
    private final String dbPassword = "";

    public boolean registration(String username_, String password_) {
        try {
            String checkQuery = "SELECT COUNT(*) FROM user_info WHERE username = ?";
            String insertUserQuery = "INSERT INTO user_info (username, password) VALUES (?, ?)";
            String[] teamQueries = {
                    "INSERT INTO `team_player` (`id`, `username`, `player_ID`, player_base_id, `active`) VALUES (NULL, ?, '33', '33', 'YES')",
                    "INSERT INTO `team_player` (`id`, `username`, `player_ID`, player_base_id, `active`) VALUES (NULL, ?, '34', '34', 'YES')",
                    "INSERT INTO `team_player` (`id`, `username`, `player_ID`, player_base_id, `active`) VALUES (NULL, ?, '35', '35', 'YES')",
                    "INSERT INTO `team_player` (`id`, `username`, `player_ID`, player_base_id, `active`) VALUES (NULL, ?, '36', '36', 'YES')",
                    "INSERT INTO `team_player` (`id`, `username`, `player_ID`, player_base_id, `active`) VALUES (NULL, ?, '37', '37', 'YES')",
                    "INSERT INTO `team_player` (`id`, `username`, `player_ID`, player_base_id, `active`) VALUES (NULL, ?, '38', '38', 'YES')",
                    "INSERT INTO `team_player` (`id`, `username`, `player_ID`, player_base_id, `active`) VALUES (NULL, ?, '39', '39', 'YES')",
                    "INSERT INTO `team_player` (`id`, `username`, `player_ID`, player_base_id, `active`) VALUES (NULL, ?, '40', '40', 'YES')",
                    "INSERT INTO `team_player` (`id`, `username`, `player_ID`, player_base_id, `active`) VALUES (NULL, ?, '41', '41', 'YES')",
                    "INSERT INTO `team_player` (`id`, `username`, `player_ID`, player_base_id, `active`) VALUES (NULL, ?, '42', '42', 'YES')",
                    "INSERT INTO `team_player` (`id`, `username`, `player_ID`, player_base_id, `active`) VALUES (NULL, ?, '43', '43', 'YES')",
            };

            // Load the JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, dbUsername, dbPassword);

            // Check if username already exists
            PreparedStatement checkStatement = connection.prepareStatement(checkQuery);
            checkStatement.setString(1, username_);
            ResultSet resultSet = checkStatement.executeQuery();

            int userCount = 0;
            if (resultSet.next()) {
                userCount = resultSet.getInt(1);
            }

            if (userCount > 0) {
                connection.close();
                return false; // Username already exists
            }

            // Start a transaction
            connection.setAutoCommit(false);

            // Insert new user
            PreparedStatement insertUserStatement = connection.prepareStatement(insertUserQuery);
            insertUserStatement.setString(1, username_);
            insertUserStatement.setString(2, password_);
            int rowsInserted = insertUserStatement.executeUpdate();

            if (rowsInserted > 0) {
                // Insert into team_player for all predefined queries
                for (String teamQuery : teamQueries) {
                    PreparedStatement teamStatement = connection.prepareStatement(teamQuery);
                    teamStatement.setString(1, username_);
                    teamStatement.executeUpdate();
                }

                // Commit the transaction
                connection.commit();
                connection.close();
                return true; // Success
            } else {
                // Rollback if user insert failed
                connection.rollback();
                connection.close();
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean login(String username_, String password_) {
        String loginQuery = "SELECT COUNT(*) FROM user_info WHERE username = ? AND password = ?";

        try {
            // Load JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish a connection
            Connection connection = DriverManager.getConnection(url, dbUsername, dbPassword);

            // Prepare the login query
            PreparedStatement loginStatement = connection.prepareStatement(loginQuery);
            loginStatement.setString(1, username_);
            loginStatement.setString(2, password_);

            // Execute the query
            ResultSet resultSet = loginStatement.executeQuery();

            int matchCount = 0;
            if (resultSet.next()) {
                matchCount = resultSet.getInt(1);
            }

            connection.close();
            return matchCount > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public String getAccountLevel(String username) {
        String accountLevel = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, dbUsername, dbPassword);
            String query = "SELECT account_level FROM user_info WHERE username = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                accountLevel = resultSet.getString("account_level");
            }

            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return accountLevel;
    }

    public int getAccountXP(String username) {
        int xp = 0;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, dbUsername, dbPassword);
            String query = "SELECT xp FROM user_info WHERE username = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                xp = resultSet.getInt("xp");
            }

            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return xp;
    }

    public String getAccountCardCount(String username) {
        String cardCount = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, dbUsername, dbPassword);
            String query = "SELECT power_pass FROM user_info WHERE username = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                cardCount = resultSet.getString("power_pass");
            }

            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cardCount;
    }

    public String getAccountCoin(String username) {
        String coins = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, dbUsername, dbPassword);
            String query = "SELECT coins FROM user_info WHERE username = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                coins = resultSet.getString("coins");
            }

            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return coins;
    }

    public List<String> getAllPlayers(String username) {
        ArrayList<String> players = new ArrayList<String>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, dbUsername, dbPassword);
            String query = "SELECT * \n" +
                    "FROM player_data pd\n" +
                    "WHERE NOT EXISTS (\n" +
                    "    SELECT 1 \n" +
                    "    FROM marketplace mp \n" +
                    "    WHERE pd.player_ID = mp.player_ID \n" +
                    "      AND mp.seller = ?\n" +
                    ") \n" +
                    "AND pd.sellable = 'YES' \n" +
                    "AND pd.in_market = 'YES';\n";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int playerId = resultSet.getInt("player_ID");
                int playerBaseId = resultSet.getInt("player_base_id");
                String img = resultSet.getString("img");
                String name = resultSet.getString("name");
                String position = resultSet.getString("position");
                int ovr = resultSet.getInt("ovr");
                String country = resultSet.getString("country");
                int price = resultSet.getInt("price");
                String base = resultSet.getString("base");
                String sellable = resultSet.getString("sellable");
                String inMarket = resultSet.getString("in_market");
                int level = resultSet.getInt("level");

                // Combine player data into a string
                String playerData = playerBaseId + "-" + img + "-" + name + "-" + position + "-" + ovr + "-" + country + "-" + price + "-" + base + "-" + playerId + "-" + level + "-" + sellable + "-" + inMarket;

                players.add(playerData);
            }

            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return players;
    }

    public List<String> searchPlayersByName(String username, String player_name) {
        ArrayList<String> players = new ArrayList<>();
        try {
            // Load MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish the connection
            Connection connection = DriverManager.getConnection(url, dbUsername, dbPassword);

            // SQL query with placeholders for username and player name
            String query = "SELECT * \n" +
                    "FROM player_data pd\n" +
                    "WHERE NOT EXISTS (\n" +
                    "    SELECT 1 \n" +
                    "    FROM marketplace mp \n" +
                    "    WHERE pd.player_ID = mp.player_ID \n" +
                    "      AND mp.seller = ?\n" +
                    ") \n" +
                    "AND pd.sellable = 'YES' \n" +
                    "AND pd.in_market = 'YES' \n" +
                    "AND pd.name LIKE ?;\n";

            // Prepare statement
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            // Set the parameters for the prepared statement
            preparedStatement.setString(1, username);  // Set the search pattern with wildcards for LIKE
            preparedStatement.setString(2, "%" + player_name + "%");  // Set the search pattern with wildcards for LIKE

            // Execute query and get the result set
            ResultSet resultSet = preparedStatement.executeQuery();

            // Process each result and add to the players list
            while (resultSet.next()) {
                int playerId = resultSet.getInt("player_ID");
                int playerBaseId = resultSet.getInt("player_base_id");
                String img = resultSet.getString("img");
                String name = resultSet.getString("name");
                String position = resultSet.getString("position");
                int ovr = resultSet.getInt("ovr");
                String country = resultSet.getString("country");
                int price = resultSet.getInt("price");
                String base = resultSet.getString("base");
                String sellable = resultSet.getString("sellable");
                String inMarket = resultSet.getString("in_market");
                int level = resultSet.getInt("level");

                // Combine player data into a string
                String playerData = playerBaseId + "-" + img + "-" + name + "-" + position + "-" + ovr + "-" + country + "-" + price + "-" + base + "-" + playerId + "-" + level + "-" + sellable + "-" + inMarket;
                players.add(playerData);
            }

            // Close the connection
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return players;
    }

    public List<String> searchPlayersByPrice(String username, String min, String max) {
        ArrayList<String> players = new ArrayList<>();
        try {
            // Load MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish the connection
            Connection connection = DriverManager.getConnection(url, dbUsername, dbPassword);

            // SQL query with placeholders for min and max prices
            String query = "SELECT * \n" +
                    "FROM player_data pd\n" +
                    "WHERE NOT EXISTS (\n" +
                    "    SELECT 1 \n" +
                    "    FROM marketplace mp \n" +
                    "    WHERE pd.player_ID = mp.player_ID \n" +
                    "      AND mp.seller = ?\n" +
                    ")\n" +
                    "AND pd.sellable = 'YES' \n" +
                    "AND pd.in_market = 'YES' \n" +
                    "AND pd.price >= ? \n" +
                    "AND pd.price <= ?;\n";

            // Prepare the statement
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            // Handle default values for min and max
            int minPrice = (min == null || min.isEmpty()) ? 0 : Integer.parseInt(min);
            int maxPrice = (max == null || max.isEmpty()) ? Integer.MAX_VALUE : Integer.parseInt(max);

            // Set the parameters for the prepared statement
            preparedStatement.setString(1, username);
            preparedStatement.setInt(2, minPrice);
            preparedStatement.setInt(3, maxPrice);

            // Execute the query and retrieve the result set
            ResultSet resultSet = preparedStatement.executeQuery();

            // Process each result and add to the players list
            while (resultSet.next()) {
                int playerId = resultSet.getInt("player_ID");
                int playerBaseId = resultSet.getInt("player_base_id");
                String img = resultSet.getString("img");
                String name = resultSet.getString("name");
                String position = resultSet.getString("position");
                int ovr = resultSet.getInt("ovr");
                String country = resultSet.getString("country");
                int price = resultSet.getInt("price");
                String base = resultSet.getString("base");
                String sellable = resultSet.getString("sellable");
                String inMarket = resultSet.getString("in_market");
                int level = resultSet.getInt("level");

                // Combine player data into a string
                String playerData = playerBaseId + "-" + img + "-" + name + "-" + position + "-" + ovr + "-" + country + "-" + price + "-" + base + "-" + playerId + "-" + level + "-" + sellable + "-" + inMarket;
                players.add(playerData);
            }

            // Close the connection
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return players;
    }

    public List<String> searchPlayersByOvr(String username, String min, String max) {
        ArrayList<String> players = new ArrayList<>();
        try {
            // Load MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish the connection
            Connection connection = DriverManager.getConnection(url, dbUsername, dbPassword);

            // SQL query with placeholders for min and max prices
            String query = "SELECT * \n" +
                    "FROM player_data pd\n" +
                    "WHERE NOT EXISTS (\n" +
                    "    SELECT 1 \n" +
                    "    FROM marketplace mp \n" +
                    "    WHERE pd.player_ID = mp.player_ID \n" +
                    "      AND mp.seller = ?\n" +
                    ")\n" +
                    "AND pd.sellable = 'YES' \n" +
                    "AND pd.in_market = 'YES' \n" +
                    "AND pd.ovr >= ? \n" +
                    "AND pd.ovr <= ?;\n";

            // Prepare the statement
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            // Handle default values for min and max
            int minOvr = (min == null || min.isEmpty()) ? 0 : Integer.parseInt(min);
            int maxOvr = (max == null || max.isEmpty()) ? Integer.MAX_VALUE : Integer.parseInt(max);

            // Set the parameters for the prepared statement
            preparedStatement.setString(1, username);
            preparedStatement.setInt(2, minOvr);
            preparedStatement.setInt(3, maxOvr);

            // Execute the query and retrieve the result set
            ResultSet resultSet = preparedStatement.executeQuery();

            // Process each result and add to the players list
            while (resultSet.next()) {
                int playerId = resultSet.getInt("player_ID");
                int playerBaseId = resultSet.getInt("player_base_id");
                String img = resultSet.getString("img");
                String name = resultSet.getString("name");
                String position = resultSet.getString("position");
                int ovr = resultSet.getInt("ovr");
                String country = resultSet.getString("country");
                int price = resultSet.getInt("price");
                String base = resultSet.getString("base");
                String sellable = resultSet.getString("sellable");
                String inMarket = resultSet.getString("in_market");
                int level = resultSet.getInt("level");

                // Combine player data into a string
                String playerData = playerBaseId + "-" + img + "-" + name + "-" + position + "-" + ovr + "-" + country + "-" + price + "-" + base + "-" + playerId + "-" + level + "-" + sellable + "-" + inMarket;
                players.add(playerData);
            }

            // Close the connection
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return players;
    }

    public List<String> searchPlayersByPosition(String username, String pos) {
        ArrayList<String> players = new ArrayList<>();
        try {
            // Load MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish the connection
            Connection connection = DriverManager.getConnection(url, dbUsername, dbPassword);

            // SQL query with placeholders for username and player name
            String query = "SELECT * \n" +
                    "FROM player_data pd\n" +
                    "WHERE NOT EXISTS (\n" +
                    "    SELECT 1 \n" +
                    "    FROM marketplace mp \n" +
                    "    WHERE pd.player_ID = mp.player_ID \n" +
                    "      AND mp.seller = ?\n" +
                    ")\n" +
                    "AND pd.sellable = 'YES' \n" +
                    "AND pd.in_market = 'YES' \n" +
                    "AND pd.position = ?;\n";

            // Prepare statement
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            // Set the parameters for the prepared statement
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, pos);  // Set the search pattern with wildcards for LIKE

            // Execute query and get the result set
            ResultSet resultSet = preparedStatement.executeQuery();

            // Process each result and add to the players list
            while (resultSet.next()) {
                int playerId = resultSet.getInt("player_ID");
                int playerBaseId = resultSet.getInt("player_base_id");
                String img = resultSet.getString("img");
                String name = resultSet.getString("name");
                String position = resultSet.getString("position");
                int ovr = resultSet.getInt("ovr");
                String country = resultSet.getString("country");
                int price = resultSet.getInt("price");
                String base = resultSet.getString("base");
                String sellable = resultSet.getString("sellable");
                String inMarket = resultSet.getString("in_market");
                int level = resultSet.getInt("level");

                // Combine player data into a string
                String playerData = playerBaseId + "-" + img + "-" + name + "-" + position + "-" + ovr + "-" + country + "-" + price + "-" + base + "-" + playerId + "-" + level + "-" + sellable + "-" + inMarket;
                players.add(playerData);
            }

            // Close the connection
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return players;
    }



    public List<String> getTeamPlayers(String username) {
        ArrayList<String> players = new ArrayList<>();
        try {
            // Load MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish the connection
            Connection connection = DriverManager.getConnection(url, dbUsername, dbPassword);

            // SQL query with placeholders for username and player name
            String query = "SELECT pd.*, tp.active, tp.pos\n" +
                    "FROM player_data pd\n" +
                    "INNER JOIN team_player tp ON pd.player_ID = tp.player_ID\n" +
                    "WHERE tp.username = ?;\n";

            // Prepare statement
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            // Set the parameters for the prepared statement
            preparedStatement.setString(1, username);  // Set the username

            // Execute query and get the result set
            ResultSet resultSet = preparedStatement.executeQuery();

            // Process each result and add to the players list
            while (resultSet.next()) {
                int playerId = resultSet.getInt("player_ID");
                int playerBaseId = resultSet.getInt("player_base_id");
                String img = resultSet.getString("img");
                String name = resultSet.getString("name");
                String position = resultSet.getString("position");
                int ovr = resultSet.getInt("ovr");
                String country = resultSet.getString("country");
                int price = resultSet.getInt("price");
                String base = resultSet.getString("base");
                String active = resultSet.getString("active");
                int pos = resultSet.getInt("pos");
                int level = resultSet.getInt("level");
                String sellable = resultSet.getString("sellable");
                String inMarket = resultSet.getString("in_market");

                // Combine player data into a string
                // Combine player data into a string
                String playerData = playerBaseId + "-" + img + "-" + name + "-" + position + "-" + ovr + "-" + country + "-" + price + "-" + base + "-" + active + "-" + pos + "-" + playerId + "-" + level + "-" + sellable + "-" + inMarket;
                players.add(playerData);
            }

            // Close the connection
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return players;
    }

    public List<String> getListingPlayers(String username){
        List<String> players = new ArrayList<>();
        String query = "SELECT pd.*, mp.seller, mp.bidder, mp.biddingPrice\n" +
                "FROM player_data pd\n" +
                "INNER JOIN marketplace mp ON pd.player_ID = mp.player_ID\n" +
                "WHERE mp.seller = ?\n" +
                "  AND mp.biddingPrice = (\n" +
                "      SELECT MAX(sub_mp.biddingPrice)\n" +
                "      FROM marketplace sub_mp\n" +
                "      WHERE sub_mp.player_ID = mp.player_ID\n" +
                "  )\n";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, dbUsername, dbPassword);
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username); // Set the username
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int playerId = resultSet.getInt("player_ID");
                int playerBaseId = resultSet.getInt("player_base_id");
                String img = resultSet.getString("img");
                String name = resultSet.getString("name");
                String position = resultSet.getString("position");
                int ovr = resultSet.getInt("ovr");
                String country = resultSet.getString("country");
                int price = resultSet.getInt("price");
                String base = resultSet.getString("base");
                String sellable = resultSet.getString("sellable");
                String inMarket = resultSet.getString("in_market");
                int level = resultSet.getInt("level");

                // Combine all fields into a single string and add to the list
                String playerData = playerBaseId + "-" + img + "-" + name + "-" + position + "-" + ovr + "-" + country + "-" + price + "-" + base + "-" + playerId + "-" + level + "-" + sellable + "-" + inMarket;

                players.add(playerData);
            }
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return players;
    }

    public List<String> getPlayersWithBiddingDetails(String Seller, int playerID) {
        List<String> players = new ArrayList<>();
        String query = "SELECT pd.*, mp.seller, mp.bidder, mp.biddingPrice " +
                "FROM player_data pd " +
                "INNER JOIN marketplace mp ON pd.player_ID = mp.player_ID "+
                "WHERE mp.seller = ? AND mp.player_ID = ? AND mp.bidder IS NOT NULL";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, dbUsername, dbPassword);
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, Seller);
            preparedStatement.setInt(2, playerID);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int playerId = resultSet.getInt("player_ID");
                String seller = resultSet.getString("seller");
                String bidder = resultSet.getString("bidder");
                int biddingPrice = resultSet.getInt("biddingPrice");

                // Combine all fields into a single string and add to the list
                String playerData = playerId + "-" + seller + "-" + bidder + "-" + biddingPrice;
                players.add(playerData);
            }

            // Close connection
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return players;
    }

    public List<String> getPlayersWithBiddingDetails(int playerID) {
        List<String> players = new ArrayList<>();
        String query = "SELECT pd.*, mp.seller, mp.bidder, mp.biddingPrice " +
                "FROM player_data pd " +
                "INNER JOIN marketplace mp ON pd.player_ID = mp.player_ID "+
                "WHERE mp.player_ID = ? AND mp.bidder IS NOT NULL";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, dbUsername, dbPassword);
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, playerID);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int playerId = resultSet.getInt("player_ID");
                String seller = resultSet.getString("seller");
                String bidder = resultSet.getString("bidder");
                int biddingPrice = resultSet.getInt("biddingPrice");

                // Combine all fields into a single string and add to the list
                String playerData = playerId + "-" + seller + "-" + bidder + "-" + biddingPrice;
                players.add(playerData);
            }

            // Close connection
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return players;
    }

    public void deleteMarketplaceEntryAndUpdate(String seller, String bidder, String playerID) {
        Connection connection = null; // Declare the connection outside the try block
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish database connection
            connection = DriverManager.getConnection(url, dbUsername, dbPassword);
            connection.setAutoCommit(false); // Begin transaction

            // Delete from marketplace
            String deleteQuery = "DELETE FROM marketplace WHERE seller = ? AND player_ID = ?";
            try (PreparedStatement deleteStatement = connection.prepareStatement(deleteQuery)) {
                deleteStatement.setString(1, seller);
                deleteStatement.setInt(2, Integer.parseInt(playerID));
                deleteStatement.executeUpdate();
            }

            // Update team_player
            String updateTeamPlayerQuery = "UPDATE team_player SET username = ?, active = 'NO', pos = 0 WHERE username = ? AND player_ID = ?";
            try (PreparedStatement updateTeamPlayerStatement = connection.prepareStatement(updateTeamPlayerQuery)) {
                updateTeamPlayerStatement.setString(1, bidder);
                updateTeamPlayerStatement.setString(2, seller);
                updateTeamPlayerStatement.setInt(3, Integer.parseInt(playerID));
                updateTeamPlayerStatement.executeUpdate();
            }

            // Update player_data
            String updatePlayerDataQuery = "UPDATE player_data SET in_market = 'NO' WHERE player_ID = ?";
            try (PreparedStatement updatePlayerDataStatement = connection.prepareStatement(updatePlayerDataQuery)) {
                updatePlayerDataStatement.setInt(1, Integer.parseInt(playerID));
                updatePlayerDataStatement.executeUpdate();
            }

            connection.commit(); // Commit transaction
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Database operation failed: " + e.getMessage());
            if (connection != null) {
                try {
                    connection.rollback(); // Rollback transaction on failure
                } catch (SQLException rollbackEx) {
                    rollbackEx.printStackTrace();
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("JDBC Driver not found: " + e.getMessage());
        } finally {
            // Close the connection in the finally block
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException closeEx) {
                    closeEx.printStackTrace();
                }
            }
        }
    }

    public void cancelListingPlayer(String seller, String playerID){
        Connection connection = null; // Declare the connection outside the try block
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish database connection
            connection = DriverManager.getConnection(url, dbUsername, dbPassword);
            connection.setAutoCommit(false); // Begin transaction

            // Delete from marketplace
            String deleteQuery = "DELETE FROM marketplace WHERE seller = ? AND player_ID = ?";
            try (PreparedStatement deleteStatement = connection.prepareStatement(deleteQuery)) {
                deleteStatement.setString(1, seller);
                deleteStatement.setInt(2, Integer.parseInt(playerID));
                deleteStatement.executeUpdate();
            }

            // Update player_data
            String updatePlayerDataQuery = "UPDATE player_data SET in_market = 'NO' WHERE player_ID = ?";
            try (PreparedStatement updatePlayerDataStatement = connection.prepareStatement(updatePlayerDataQuery)) {
                updatePlayerDataStatement.setInt(1, Integer.parseInt(playerID));
                updatePlayerDataStatement.executeUpdate();
            }

            connection.commit(); // Commit transaction
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Database operation failed: " + e.getMessage());
            if (connection != null) {
                try {
                    connection.rollback(); // Rollback transaction on failure
                } catch (SQLException rollbackEx) {
                    rollbackEx.printStackTrace();
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("JDBC Driver not found: " + e.getMessage());
        } finally {
            // Close the connection in the finally block
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException closeEx) {
                    closeEx.printStackTrace();
                }
            }
        }
    }


    public Map<String, List<String>> getTeamPlayersByPosition(String username) {
        Map<String, List<String>> playersByPosition = new HashMap<>();
        playersByPosition.put("ST", new ArrayList<>());
        playersByPosition.put("CM", new ArrayList<>());
        playersByPosition.put("CB", new ArrayList<>());
        playersByPosition.put("GK", new ArrayList<>());

        try {
            // Load MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish the connection
            Connection connection = DriverManager.getConnection(url, dbUsername, dbPassword);

            // SQL query with placeholders for username
            String query = "SELECT pd.*, tp.active, tp.pos\n" +
                    "FROM player_data pd\n" +
                    "INNER JOIN team_player tp ON pd.player_ID = tp.player_ID\n" +
                    "WHERE tp.username = ?;";

            // Prepare statement
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            // Set the parameters for the prepared statement
            preparedStatement.setString(1, username);

            // Execute query and get the result set
            ResultSet resultSet = preparedStatement.executeQuery();

            // Process each result and categorize by position
            while (resultSet.next()) {
                int playerId = resultSet.getInt("player_ID");
                int playerBaseId = resultSet.getInt("player_base_id");
                String img = resultSet.getString("img");
                String name = resultSet.getString("name");
                String position = resultSet.getString("position");
                int ovr = resultSet.getInt("ovr");
                String country = resultSet.getString("country");
                int price = resultSet.getInt("price");
                String base = resultSet.getString("base");
                String active = resultSet.getString("active");
                int pos = resultSet.getInt("pos");
                int level = resultSet.getInt("level");
                String sellable = resultSet.getString("sellable");
                String inMarket = resultSet.getString("in_market");

                // Combine player data into a string
                String playerData = playerBaseId + "-" + img + "-" + name + "-" + position + "-" + ovr + "-" + country + "-" + price + "-" + base + "-" + active + "-" + pos + "-" + playerId + "-" + level + "-" + sellable + "-" + inMarket;

                // Add the player to the appropriate position list
                if (playersByPosition.containsKey(position)) {
                    playersByPosition.get(position).add(playerData);
                }
            }

            // Close the connection
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return playersByPosition;
    }

    public void updateActivePlayers(ArrayList<String> playersID, String username){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection connection = DriverManager.getConnection(url, dbUsername, dbPassword);
            String query = "UPDATE team_player SET active = 'NO' WHERE username = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.executeUpdate();

            String updateQuery = "UPDATE team_player SET active = 'YES', pos = ? WHERE player_base_id = ? AND username = ?";
            PreparedStatement updateStatement = connection.prepareStatement(updateQuery);

            for(String playerID : playersID){
                String[] parts = playerID.split("-");
                updateStatement.setString(1, parts[1]); // Set position
                updateStatement.setString(2, parts[0]); // Set player ID
                updateStatement.setString(3, username); // Set username
                updateStatement.addBatch(); // Add query to batch
            }
            updateStatement.executeBatch();

            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void updateAccountCoin(String username, String newCoinBalance) {
        String query = "UPDATE user_info SET coins = ? WHERE username = ?";

        try (Connection connection = DriverManager.getConnection(url, dbUsername, dbPassword);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, newCoinBalance);
            preparedStatement.setString(2, username);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Updated coin balance successfully for user: " + username);
            } else {
                System.out.println("No rows affected. User may not exist: " + username);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error updating coin balance: " + e.getMessage());
        }
    }

    public void updateAccountXP(String username, int newXP) {
        String query = "UPDATE user_info SET xp = ? WHERE username = ?";

        try (Connection connection = DriverManager.getConnection(url, dbUsername, dbPassword);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, newXP);
            preparedStatement.setString(2, username);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Updated coin balance successfully for user: " + username);
            } else {
                System.out.println("No rows affected. User may not exist: " + username);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error updating coin balance: " + e.getMessage());
        }
    }

    public void updateAccountCard(String username, String newCardBalance) {
        String query = "UPDATE user_info SET power_pass = ? WHERE username = ?";

        try (Connection connection = DriverManager.getConnection(url, dbUsername, dbPassword);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, newCardBalance);
            preparedStatement.setString(2, username);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Updated coin balance successfully for user: " + username);
            } else {
                System.out.println("No rows affected. User may not exist: " + username);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error updating coin balance: " + e.getMessage());
        }
    }

    public boolean insertIntoTeamPlayer(String username, String playerId, String player_base_id) {
        String query = "INSERT INTO team_player (username, player_ID, player_base_id) VALUES (?, ?, ?)";
        boolean isInserted = false;

        try (Connection connection = DriverManager.getConnection(url, dbUsername, dbPassword);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            // Set parameters
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, playerId);
            preparedStatement.setString(3, player_base_id);

            // Execute update
            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Player " + playerId + " added to the team for user: " + username);
                isInserted = true;
            } else {
                System.out.println("No rows affected while adding player " + playerId + " for user: " + username);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error while inserting into team_player table: " + e.getMessage());
        }

        return isInserted;
    }

    public void upgradeFromBasePlayer(String playerID, String username) {
        try {
            // Load MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish database connection
            Connection connection = DriverManager.getConnection(url, dbUsername, dbPassword);

            // Query to get the player data
            String query = "SELECT * FROM player_data WHERE player_ID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, Integer.parseInt(playerID));
            ResultSet resultSet = preparedStatement.executeQuery();

            // Check if the result set has data
            if (resultSet.next()) {
                int fetchedBaseId = resultSet.getInt("player_base_id");
                String img = resultSet.getString("img");
                String name = resultSet.getString("name");
                String position = resultSet.getString("position");
                int ovr = resultSet.getInt("ovr");
                String country = resultSet.getString("country");
                int price = resultSet.getInt("price");
                String base = resultSet.getString("base");
                String sellable = resultSet.getString("sellable");
                String inMarket = resultSet.getString("in_market");
                int level = resultSet.getInt("level");

                // Query to insert the upgraded player data and return the generated keys
                query = "INSERT INTO player_data (player_base_id, img, name, position, ovr, country, price, base, sellable, in_market, level) " +
                        "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

                // Set parameters for the new player
                preparedStatement.setInt(1, fetchedBaseId);
                preparedStatement.setString(2, img);
                preparedStatement.setString(3, name);
                preparedStatement.setString(4, position);
                preparedStatement.setInt(5, ovr + 1); // Increment OVR
                preparedStatement.setString(6, country);
                preparedStatement.setInt(7, price);
                preparedStatement.setString(8, "NO");
                preparedStatement.setString(9, sellable);
                preparedStatement.setString(10, "NO");
                preparedStatement.setInt(11, level + 1); // Increment Level

                // Execute the insertion
                preparedStatement.executeUpdate();

                // Fetch the generated keys
                ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int newPlayerID = generatedKeys.getInt(1); // The new player_ID

                    // Query to update the team_player with the new player_ID
                    query = "UPDATE team_player SET player_ID = ? WHERE username = ? AND player_base_id = ?";
                    preparedStatement = connection.prepareStatement(query);

                    preparedStatement.setInt(1, newPlayerID);
                    preparedStatement.setString(2, username);
                    preparedStatement.setInt(3, fetchedBaseId);

                    // Use executeUpdate instead of executeQuery
                    preparedStatement.executeUpdate();
                }
            }

            // Close the connection
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void upgradeFromPlayer(String playerID, String ovr, String level, String username){
        String query = "UPDATE player_data SET ovr = ?, level = ? WHERE player_ID = ?";

        try {
            Connection connection = DriverManager.getConnection(url, dbUsername, dbPassword);
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setInt(1, Integer.parseInt(ovr)+1);
            preparedStatement.setInt(2, Integer.parseInt(level)+1);
            preparedStatement.setInt(3, Integer.parseInt(playerID));

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error updating coin balance: " + e.getMessage());
        }
    }

    public void deletePlayerFromTeam(String username, String playerID) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection connection = DriverManager.getConnection(url, dbUsername, dbPassword);
            String query = "DELETE FROM team_player WHERE username = ? AND player_ID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, username);
            preparedStatement.setInt(2, Integer.parseInt(playerID));

            preparedStatement.executeUpdate();
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void insertIntoMarketPlaceAndUpdatePlayerData(String username, String playerId, int price) {
        String query = "INSERT INTO marketplace (seller, player_ID) VALUES (?, ?)";
        try (Connection connection = DriverManager.getConnection(url, dbUsername, dbPassword);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, playerId);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error while inserting into team_player table: " + e.getMessage());
        }

        query = "UPDATE player_data set in_market = 'YES', price = ? where player_ID = ?";
        try (Connection connection = DriverManager.getConnection(url, dbUsername, dbPassword);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, price);
            preparedStatement.setString(2, playerId);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error while inserting into team_player table: " + e.getMessage());
        }
    }

    public void placeBidonMarketplace(String playerID, String bidder, int biddingAmount) {
        String seller = null; // Variable to store the seller's information
        Connection connection = null;

        try {
            // Establish connection
            connection = DriverManager.getConnection(url, dbUsername, dbPassword);

            // Disable auto-commit for transaction
            connection.setAutoCommit(false);

            // Step 1: Get the seller for the specified player ID
            String selectQuery = "SELECT seller FROM marketplace WHERE player_ID = ? LIMIT 1";
            try (PreparedStatement selectStatement = connection.prepareStatement(selectQuery)) {
                selectStatement.setString(1, playerID);
                try (ResultSet resultSet = selectStatement.executeQuery()) {
                    if (resultSet.next()) {
                        seller = resultSet.getString("seller");
                    } else {
                        throw new SQLException("Player ID not found in marketplace.");
                    }
                }
            }

            // Step 2: Insert the new bid into the marketplace table
            String insertQuery = "INSERT INTO marketplace (seller, player_ID, bidder, biddingPrice) VALUES (?, ?, ?, ?)";
            try (PreparedStatement insertStatement = connection.prepareStatement(insertQuery)) {
                insertStatement.setString(1, seller); // Use the fetched seller
                insertStatement.setString(2, playerID);
                insertStatement.setString(3, bidder);
                insertStatement.setInt(4, biddingAmount);
                insertStatement.executeUpdate();
            }

            // Commit transaction
            connection.commit();

        } catch (SQLException e) {
            e.printStackTrace();
            if (connection != null) {
                try {
                    connection.rollback(); // Rollback transaction in case of error
                } catch (SQLException rollbackEx) {
                    rollbackEx.printStackTrace();
                }
            }
        } finally {
            if (connection != null) {
                try {
                    // Restore default auto-commit behavior
                    connection.setAutoCommit(true);
                    connection.close();
                } catch (SQLException closeEx) {
                    closeEx.printStackTrace();
                }
            }
        }
    }

    public List<String> getBidPlayers(String username) {
        List<String> players = new ArrayList<>();
        String query = "SELECT pd.*, mp.seller, mp.bidder, mp.biddingPrice " +
                "FROM player_data pd " +
                "INNER JOIN marketplace mp ON pd.player_ID = mp.player_ID " +
                "WHERE mp.bidder = ? " +
                "  AND mp.biddingPrice = (" +
                "      SELECT MAX(sub_mp.biddingPrice) " +
                "      FROM marketplace sub_mp " +
                "      WHERE sub_mp.player_ID = mp.player_ID " +
                "  )";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, dbUsername, dbPassword);
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username); // Set the username
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int playerId = resultSet.getInt("player_ID");
                String img = resultSet.getString("img");
                String name = resultSet.getString("name");
                String position = resultSet.getString("position");
                int ovr = resultSet.getInt("ovr");
                int biddingPrice = resultSet.getInt("biddingPrice");

                // Combine all fields into a single string and add to the list
                String playerData = playerId + "-" + img + "-" + name + "-" + position + "-" + ovr + "-" + biddingPrice;

                players.add(playerData);
            }
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return players;
    }

    public void cancelBidPlayer(String bidder, String playerID){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection connection = DriverManager.getConnection(url, dbUsername, dbPassword);
            String query = "DELETE FROM marketplace WHERE bidder = ? AND player_ID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, bidder);
            preparedStatement.setInt(2, Integer.parseInt(playerID));

            preparedStatement.executeUpdate();
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getTeamValue(String username) {
        int totalPrice = 0;
        String query = "SELECT SUM(pd.price) AS total_price " +
                "FROM player_data pd " +
                "INNER JOIN team_player tp ON pd.player_ID = tp.player_ID " +
                "WHERE tp.username = ? AND tp.active = 'YES'";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, dbUsername, dbPassword);
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username); // Set the username
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                totalPrice = resultSet.getInt("total_price"); // Get the sum of active player prices
            }

            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return totalPrice;
    }

    public int getTeamOvr(String username) {
        int totalOVR = 0;
        String query = "SELECT SUM(pd.ovr) AS total_ovr " +
                "FROM player_data pd " +
                "INNER JOIN team_player tp ON pd.player_ID = tp.player_ID " +
                "WHERE tp.username = ? AND tp.active = 'YES'";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, dbUsername, dbPassword);
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username); // Set the username
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                totalOVR = resultSet.getInt("total_ovr"); // Get the sum of active player OVRs
            }

            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return totalOVR;
    }

    public String getDivisionRivalInfo(String username) {
        String playerData = "";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, dbUsername, dbPassword);
            String query = "SELECT * from multiplayer WHERE username = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int rank = resultSet.getInt("rank");
                String score = resultSet.getString("score_value");

                // Combine player data into a string
                playerData = rank + ":" + score;
            }

            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return playerData;
    }

    public List<String> getMatchHistory(String username) {
        List<String> matches = new ArrayList<>();
        String query = "SELECT * FROM match_history WHERE me = ?";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, dbUsername, dbPassword);
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username); // Set the username
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String me = resultSet.getString("me");
                int my_score = resultSet.getInt("my_score");
                String opponent = resultSet.getString("opponent");
                int opponent_score = resultSet.getInt("opponent_score");

                String playerData = me + "-" + my_score + "-" + opponent + "-" + opponent_score;

                matches.add(playerData);
            }
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return matches;
    }

    public Date getUserDateAndSpin(String username) {
        Date userData = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection connection = DriverManager.getConnection(url, dbUsername, dbPassword);

            String query = "SELECT Date FROM user_info WHERE username = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username); // Set username parameter
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Date date = resultSet.getDate("Date");
                userData = date;
            }

            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return userData;
    }

    public void updateDate(String username, LocalDate date){
        String query = "UPDATE user_info SET Date = ? WHERE username = ?";

        try (Connection connection = DriverManager.getConnection(url, dbUsername, dbPassword);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setDate(1, Date.valueOf(date));
            preparedStatement.setString(2, username);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error updating coin balance: " + e.getMessage());
        }
    }

    public List<String> getAllUsernames() {
        List<String> usernames = new ArrayList<>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection connection = DriverManager.getConnection(url, dbUsername, dbPassword);

            String query = "SELECT username FROM user_info";
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String username = resultSet.getString("username");
                usernames.add(username);
            }

            // Close the connection
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return usernames;
    }

    public List<Message> getMessagesBetweenUsers(String user1, String user2) {
        List<Message> messages = new ArrayList<>();

        try {
            // Connect to database and run query
            Connection connection = DriverManager.getConnection(url, dbUsername, dbPassword);

            String query = "SELECT sender, message_content FROM messenger " +
                    "WHERE (sender = ? AND receiver = ?) OR (sender = ? AND receiver = ?) " +
                    "ORDER BY time_date"; // Assuming `date_time` column is the actual timestamp column

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, user1);
            preparedStatement.setString(2, user2);
            preparedStatement.setString(3, user2);
            preparedStatement.setString(4, user1);

            ResultSet resultSet = preparedStatement.executeQuery();

            // Extracting messages and sender info from the result set
            while (resultSet.next()) {
                String sender = resultSet.getString("sender");
                String messageContent = resultSet.getString("message_content");
                messages.add(new Message(sender, messageContent)); // Store sender and message content
            }

            // Closing resources
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return messages;
    }



    public void saveMessage(String sender, String receiver, String message) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, dbUsername, dbPassword);

            String query = "INSERT INTO messenger (sender, receiver, message_content) " +
                    "VALUES (?, ?, ?)";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, sender);
            preparedStatement.setString(2, receiver);
            preparedStatement.setString(3, message);
            preparedStatement.executeUpdate();

            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
