package com.driver;

import org.apache.commons.lang3.tuple.Pair;
import java.util.*;

public class Workspace extends Gmail {

    private ArrayList<Meeting> calendar; // Stores all the meetings

    public Workspace(String emailId) {
        // The inboxCapacity is equal to the maximum value an integer can store.
        super(emailId, Integer.MAX_VALUE);
        calendar = new ArrayList<>();
    }

    public void addMeeting(Meeting meeting) {
        //add the meeting to calendar
        calendar.add(meeting);
    }

    public int findMaxMeetings() {
        // find the maximum number of meetings you can attend
        // 1. At a particular time, you can be present in at most one meeting
        // 2. If you want to attend a meeting, you must join it at its start time and leave at end time.
        // Example: If a meeting ends at 10:00 am, you cannot attend another meeting starting at 10:00 am
        if (calendar.size() == 0) return 0;

        calendar.sort((a, b) -> {
            if (a.getStartTime().compareTo(b.getStartTime()) == 0) return a.getEndTime().compareTo(b.getEndTime());
            return a.getStartTime().compareTo(b.getStartTime());
        });

        PriorityQueue<Meeting> pq = new PriorityQueue<>((a, b) -> {
            if (a.getStartTime().compareTo(b.getStartTime()) == 0) return b.getEndTime().compareTo(a.getEndTime());
            return b.getStartTime().compareTo(a.getStartTime());
        });

        int count = 0;
        pq.add(calendar.get(0));
        for (int i = 1; i < calendar.size(); i++) {
            Meeting meet = calendar.get(i);
            if (pq.size() > 0 && pq.peek().getEndTime().compareTo(meet.getStartTime()) < 0) {
                pq.add(meet);
            } else if (pq.size() > 0 && pq.peek().getEndTime().compareTo(meet.getEndTime()) > 0) {
                pq.remove();
                pq.add(meet);
            }
            count = Math.max(count, pq.size());
        }
//        Meeting oldMeet = calendar.get(0);
//        for (int i = 1; i <= calendar.size() - 1; i++) {
//            Meeting nextMeet = calendar.get(i);
//            if (oldMeet.getEndTime().compareTo(nextMeet.getStartTime()) < 0) {
//                oldMeet = nextMeet;
//                count++;
//            }
//        }
        return count;
    }
}
