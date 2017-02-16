package com.semakin.task1.threading;

import java.util.List;
import java.util.concurrent.Future;

/**
 * @author Семакин Виктор
 */

public class ThreadingState {
    private List<Future<Boolean>> futureResults;

    public ThreadingState(List<Future<Boolean>> futureResults) {
        this.futureResults = futureResults;
    }

    public boolean isDone(){

        for (Future<Boolean> futureResult :
                futureResults) {
            System.out.println("проверяется " + futureResult.isDone());
            if(!futureResult.isDone()){
                return false;
            }
        }

        return true;
    }


}