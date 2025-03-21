package com.example.loginpage;

import javafx.scene.image.ImageView;

public class Person {

    private String player_base_id;
    private ImageView img;
    private String name;
    private String position;
    private String ovr;
    private String country;
    private String price;
    private String base;
    private String active;
    private String pos;
    private String playerID;
    private String level;
    private String sellable;
    private String inMarket;

    private String biddingPrice;
    private String seller;
    private String bidder;

    Person(String player_base_id, ImageView img, String name, String position, String ovr, String country, String price, String base, String active, String pos, String playerID, String level, String sellable, String inMarket){
        this.player_base_id = player_base_id;
        this.img = img;
        this.name = name;
        this.position = position;
        this.ovr = ovr;
        this.country = country;
        this.base = base;
        this.price = price;
        this.active = active;
        this.pos = pos;
        this.playerID = playerID;
        this.level = level;
        this.sellable = sellable;
        this.inMarket = inMarket;
    }

    Person(String bidder, String seller, String biddingPrice, String playerID) {
        this.bidder = bidder;
        this.seller = seller;
        this.biddingPrice = biddingPrice;
        this.playerID = playerID;
    }

    public Person(ImageView img, String name, String position, String ovr, String playerID, String biddingPrice) {
        this.img = img;
        this.name = name;
        this.position = position;
        this.ovr = ovr;
        this.playerID = playerID;
        this.biddingPrice = biddingPrice;
    }


    public String getName() {
        return name;
    }

    public String getPosition() {
        return position;
    }

    public String getOvr() {
        return ovr;
    }

    public String getPlayer_base_id() {
        return player_base_id;
    }

    public String getCountry() {
        return country;
    }

    public String getPrice() {
        return price;
    }

    public String getBase() {
        return base;
    }

    public ImageView getImg() {
        return img;
    }

    public String getActive() {
        return active;
    }

    public String getPos() {
        return pos;
    }

    public String getLevel() {
        return level;
    }

    public String getPlayerID() {
        return playerID;
    }

    public String getSellable() {
        return sellable;
    }

    public String getInMarket() {
        return inMarket;
    }

    public String getBiddingPrice() {
        return biddingPrice;
    }

    public String getSeller() {
        return seller;
    }

    public String getBidder() {
        return bidder;
    }

    public void setPlayer_base_id(String player_base_id) {
        this.player_base_id = player_base_id;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPosition(String age) {
        this.position = position;
    }

    public void setOvr(String ovr) {
        this.ovr = ovr;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public void setPos(String pos) {
        this.pos = pos;
    }

    public void setImg(ImageView img) {
        this.img = img;
    }

    public void setPlayerID(String playerID) {
        this.playerID = playerID;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public void setSellable(String sellable) {
        this.sellable = sellable;
    }

    public void setInMarket(String inMarket) {
        this.inMarket = inMarket;
    }

    public void setBiddingPrice(String biddingPrice) {
        this.biddingPrice = biddingPrice;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }

    public void setBidder(String bidder) {
        this.bidder = bidder;
    }

    public String toString() //prints object in given format
    {
        String playerData = player_base_id + "-" + img + "-" + name + "-" + position + "-" + ovr + "-" + country + "-" + price + "-" + base;
        return playerData;
    }

}
