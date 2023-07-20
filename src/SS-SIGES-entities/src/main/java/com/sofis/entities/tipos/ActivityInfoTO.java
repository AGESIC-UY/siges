package com.sofis.entities.tipos;

import java.io.Serializable;
import java.util.List;

public class ActivityInfoTO implements Serializable {

	private String description;
	private String text;
	private String cardId;
	private String listId;
	private String boardId;
	private String userId;
	private String user;
	private String card;
	private String board;
	private String swimlaneId;
	private String swimlane;
	private String commentId;
	private String comment;
	private String activityId;
	private String oldListId;
	private List<EmailDTO> userEmails;
	private String url;
	private String list;
	private String oldList;
	private String oldSwimlane;
	private String oldSwimlaneId;
	private List<String> watchers;
	private Object timeValue;
	private Object timeOldValue;
	private String checklist;
	private String checklistItem;
	private String attachment;
	private String attachmentId;
	private String customField;
	private String customFieldValue;

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getCardId() {
		return cardId;
	}

	public void setCardId(String cardId) {
		this.cardId = cardId;
	}

	public String getListId() {
		return listId;
	}

	public void setListId(String listId) {
		this.listId = listId;
	}

	public String getBoardId() {
		return boardId;
	}

	public void setBoardId(String boardId) {
		this.boardId = boardId;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getCard() {
		return card;
	}

	public void setCard(String card) {
		this.card = card;
	}

	public String getSwimlaneId() {
		return swimlaneId;
	}

	public void setSwimlaneId(String swimlaneId) {
		this.swimlaneId = swimlaneId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCommentId() {
		return commentId;
	}

	public void setCommentId(String commentId) {
		this.commentId = commentId;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getActivityId() {
		return activityId;
	}

	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}

	public String getOldListId() {
		return oldListId;
	}

	public void setOldListId(String oldListId) {
		this.oldListId = oldListId;
	}

	public List<EmailDTO> getUserEmails() {
		return userEmails;
	}

	public void setUserEmails(List<EmailDTO> userEmails) {
		this.userEmails = userEmails;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getList() {
		return list;
	}

	public void setList(String list) {
		this.list = list;
	}

	public String getOldList() {
		return oldList;
	}

	public void setOldList(String oldList) {
		this.oldList = oldList;
	}

	public String getOldSwimlane() {
		return oldSwimlane;
	}

	public void setOldSwimlane(String oldSwimlane) {
		this.oldSwimlane = oldSwimlane;
	}

	public String getOldSwimlaneId() {
		return oldSwimlaneId;
	}

	public void setOldSwimlaneId(String oldSwimlaneId) {
		this.oldSwimlaneId = oldSwimlaneId;
	}

	public List<String> getWatchers() {
		return watchers;
	}

	public void setWatchers(List<String> watchers) {
		this.watchers = watchers;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getBoard() {
		return board;
	}

	public void setBoard(String board) {
		this.board = board;
	}

	public String getSwimlane() {
		return swimlane;
	}

	public void setSwimlane(String swimlane) {
		this.swimlane = swimlane;
	}

	public Object getTimeValue() {
		return timeValue;
	}

	public void setTimeValue(Object timeValue) {
		this.timeValue = timeValue;
	}

	public Object getTimeOldValue() {
		return timeOldValue;
	}

	public void setTimeOldValue(Object timeOldValue) {
		this.timeOldValue = timeOldValue;
	}

	public String getChecklist() {
		return checklist;
	}

	public void setChecklist(String checklist) {
		this.checklist = checklist;
	}

	public String getChecklistItem() {
		return checklistItem;
	}

	public void setChecklistItem(String checklistItem) {
		this.checklistItem = checklistItem;
	}

	public String getAttachment() {
		return attachment;
	}

	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}

	public String getAttachmentId() {
		return attachmentId;
	}

	public void setAttachmentId(String attachmentId) {
		this.attachmentId = attachmentId;
	}

	public String getCustomField() {
		return customField;
	}

	public void setCustomField(String customField) {
		this.customField = customField;
	}

        public String getCustomFieldValue() {
            return customFieldValue;
        }

        public void setCustomFieldValue(String customFieldValue) {
            this.customFieldValue = customFieldValue;
        }

        
	@Override
	public String toString() {
		String result = "WekanDTO{"
				+ "description=" + description + ", text=" + text + ", cardId=" + cardId + ", listId=" + listId
				+ ", boardId=" + boardId + ", userId=" + userId + ", user=" + user
				+ ", card=" + card + ", board=" + board + ", swimlaneId=" + swimlaneId
				+ ", swimlane=" + swimlane + ", commentId=" + commentId
				+ ", comment=" + comment + ", activityId=" + activityId + ", oldListId=" + oldListId
				+ ", userEmails=" + userEmails + ", url=" + url + ", list=" + list
				+ ", oldList=" + oldList + ", oldSwimlane=" + oldSwimlane
				+ ", oldSwimlaneId=" + oldSwimlaneId + ", watchers=" + watchers
				+ ", timeValue=" + timeValue + ", timeOldValue=" + timeOldValue
				+ ", checklist=" + checklist + ", checklistItem=" + checklistItem
				+ ", attachment=" + attachment + ", attachmentId=" + attachmentId;
                                if (customField != null){
				result = result + ", customField=" + customField + '}';
                                }else {
                                    result = result + ", customField=" + customFieldValue + '}';
                                }
                return result;
	}

}