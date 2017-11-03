package com.example.asmaa.souq.Responses;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Asmaa on 17-Oct-17.
 */

public class ShipmentsResponse {




        @SerializedName("id")
        public int id;
        @SerializedName("user_id")
        public int user_id;
        @SerializedName("price")
        public int price;
        @SerializedName("type")
        public String type;
        @SerializedName("start_point_latitude")
        public double start_point_latitude;
        @SerializedName("start_point_longitude")
        public double start_point_longitude;
        @SerializedName("from")
        public String from;
        @SerializedName("end_point_latitude")
        public double end_point_latitude;
        @SerializedName("end_point_longitude")
        public double end_point_longitude;
        @SerializedName("to")
        public String to;
        @SerializedName("status")
        public String status;
        @SerializedName("load_type")
        public String load_type;
        @SerializedName("rate")
        public int rate;
        @SerializedName("notes")
        public String notes;
        @SerializedName("arrival_time")
        public String arrival_time;
        @SerializedName("inital_time")
        public String inital_time;
        @SerializedName("created_at")
        public String created_at;
        @SerializedName("updated_at")
        public String updated_at;
        @SerializedName("hasAccepted")
        public boolean hasAccepted;
        @SerializedName("shipment_offers")
        public List<Shipment_offers> shipment_offers; // enty 3ahza deh ? L2 DA DY LIST TANYA  , M3 E5a7laAs Gswany
        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public double getStart_point_latitude() {
            return start_point_latitude;
        }

        public void setStart_point_latitude(double start_point_latitude) {
            this.start_point_latitude = start_point_latitude;
        }

        public double getStart_point_longitude() {
            return start_point_longitude;
        }

        public void setStart_point_longitude(double start_point_longitude) {
            this.start_point_longitude = start_point_longitude;
        }

        public String getFrom() {
            return from;
        }

        public void setFrom(String from) {
            this.from = from;
        }

        public double getEnd_point_latitude() {
            return end_point_latitude;
        }

        public void setEnd_point_latitude(double end_point_latitude) {
            this.end_point_latitude = end_point_latitude;
        }

        public double getEnd_point_longitude() {
            return end_point_longitude;
        }

        public void setEnd_point_longitude(double end_point_longitude) {
            this.end_point_longitude = end_point_longitude;
        }

        public String getTo() {
            return to;
        }

        public void setTo(String to) {
            this.to = to;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getLoad_type() {
            return load_type;
        }

        public void setLoad_type(String load_type) {
            this.load_type = load_type;
        }

        public int getRate() {
            return rate;
        }

        public void setRate(int rate) {
            this.rate = rate;
        }

        public String getNotes() {
            return notes;
        }

        public void setNotes(String notes) {
            this.notes = notes;
        }

        public String getArrival_time() {
            return arrival_time;
        }

        public void setArrival_time(String arrival_time) {
            this.arrival_time = arrival_time;
        }

        public String getInital_time() {
            return inital_time;
        }

        public void setInital_time(String inital_time) {
            this.inital_time = inital_time;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public String getUpdated_at() {
            return updated_at;
        }

        public void setUpdated_at(String updated_at) {
            this.updated_at = updated_at;
        }

        public boolean isHasAccepted() {
            return hasAccepted;
        }

        public void setHasAccepted(boolean hasAccepted) {
            this.hasAccepted = hasAccepted;
        }

        public List<Shipment_offers> getShipment_offers() {
            return shipment_offers;
        }

        public void setShipment_offers(List<Shipment_offers> shipment_offers) {
            this.shipment_offers = shipment_offers;
        }

        public static class Shipment_offers {
        }


    }



