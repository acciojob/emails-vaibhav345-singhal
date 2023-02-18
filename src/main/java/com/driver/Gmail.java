package com.driver;

import java.util.*;

public class Gmail extends Email {


    static class EmailDetails {
        Date date;
        String sender;
        String message;

        EmailDetails(Date date, String sender, String message) {
            this.date = date;
            this.sender = sender;
            this.message = message;
        }
    }

    int inboxCapacity; //maximum number of mails inbox can store
    Deque<EmailDetails> emailList;
    Deque<EmailDetails> trashList;

    //Inbox: Stores mails. Each mail has date (Date), sender (String), message (String). It is guaranteed that message is distinct for all mails.
    //Trash: Stores mails. Each mail has date (Date), sender (String), message (String)
    public Gmail(String emailId, int inboxCapacity) {
        super(emailId);
        this.inboxCapacity = inboxCapacity;
        emailList = new ArrayDeque<>();
        trashList = new ArrayDeque<>();
    }

    public void receiveMail(Date date, String sender, String message) {
        // If the inbox is full, move the oldest mail in the inbox to trash and add the new mail to inbox.
        // It is guaranteed that:
        // 1. Each mail in the inbox is distinct.
        // 2. The mails are received in non-decreasing order. This means that the date of a new mail is greater than equal to the dates of mails received already.
        if (emailList.size() == this.inboxCapacity) {
            EmailDetails email = emailList.getFirst();
            trashList.add(email);
            EmailDetails newEmail = new EmailDetails(date, sender, message);
            emailList.addLast(newEmail);
        } else {
            EmailDetails newEmail = new EmailDetails(date, sender, message);
            emailList.addLast(newEmail);
        }
    }

    public void deleteMail(String message) {
        // Each message is distinct
        // If the given message is found in any mail in the inbox, move the mail to trash, else do nothing

    }

    public String findLatestMessage() {
        // If the inbox is empty, return null
        // Else, return the message of the latest mail present in the inbox
        if (emailList.size() == 0) return null;
        else {
            EmailDetails em = emailList.getLast();
            return em.message;
        }
    }

    public String findOldestMessage() {
        // If the inbox is empty, return null
        // Else, return the message of the oldest mail present in the inbox
        if (emailList.size() == 0) return null;
        else {
            EmailDetails em = emailList.getFirst();
            return em.message;
        }
    }

    public int findMailsBetweenDates(Date start, Date end) {
        //find number of mails in the inbox which are received between given dates
        //It is guaranteed that start date <= end date
        int count = 0;
        for (EmailDetails em : emailList) {
            if (em.date.compareTo(start) >= 0 && em.date.compareTo(end) <= 0) {
                count++;
            }
        }
        return count;
    }

    public int getInboxSize() {
        // Return number of mails in inbox
        return emailList.size();
    }

    public int getTrashSize() {
        // Return number of mails in Trash
        return trashList.size();
    }

    public void emptyTrash() {
        // clear all mails in the trash
        trashList.clear();
    }

    public int getInboxCapacity() {
        // Return the maximum number of mails that can be stored in the inbox
        return this.inboxCapacity;
    }
}
