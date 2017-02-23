package com.semakin.labs.lab2.XmlListEntities;

import com.semakin.labs.lab2.entities.InterviewResult;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * @author Семакин Виктор
 */
@XmlRootElement(name = "interviewresults")
public class InterviewResultList{
    List<InterviewResult> interviewResults;

    @XmlElement(name = "interviewresult")
    public void setInterviewResults(List<InterviewResult> interviewResults) {
        this.interviewResults = interviewResults;
    }

    public List<InterviewResult> getInterviewResults() {
        return interviewResults;
    }
}
