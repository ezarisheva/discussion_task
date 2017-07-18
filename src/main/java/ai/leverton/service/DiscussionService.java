package ai.leverton.service;

import ai.leverton.domain.Discussion;
import ai.leverton.domain.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DiscussionService {

    @Autowired
    private MessageService messageService;

    public Discussion<Message> startDiscussion(String text) {

        Message msg = messageService.startDiscussion(text);
        Discussion<Message> discussion = new Discussion<Message>(msg);
        return discussion;

    }

    public Discussion<Message> addAnswer(Discussion<Message> discussion, String text) {

        Message msg = messageService.startDiscussion(text);
        discussion.addChild(msg);
        return discussion;

    }

    public void removeMsg(Discussion<Message> discussion, Message message) {
            discussion.removeChild(message);
    }

    public int getNumberSubEntries(Discussion<Message> discussion, int number){
        number += discussion.getChildren().size();
        for(Discussion<Message> disc : discussion.getChildren()){
            getNumberSubEntries(disc, number);
        }
        return number;

    }
}
