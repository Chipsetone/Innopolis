package com.semakin.labs.lab2.XmlListEntities;

import com.semakin.labs.lab2.entities.Interview;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * @author Семакин Виктор
 */
@XmlRootElement(name = "interviews")
public class InterviewList {
    private List<Interview> interviewList;

    @XmlElement(name="interview")
    public void setInterviewList(List<Interview> interviewList) {
        this.interviewList = interviewList;
    }

    public List<Interview> getInterviewList(){
        return interviewList;
    }

}
