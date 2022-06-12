package cogitans.codingtestjava.programmers;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 기능 개발
 * https://programmers.co.kr/learn/courses/30/lessons/42586
 */

public class P42586 {
    public int[] solution(int[] progresses, int[] speeds) {
        WorkList workList = new WorkList();
        List<Integer> answerList = new ArrayList<>();

        for (int i = 0; i < progresses.length; i++) {
            workList.add(new Work(progresses[i], speeds[i]));
        }

        while (workList.hasNext()) {
            int sprintResult = workList.sprint();
            if (sprintResult != 0) {
                answerList.add(sprintResult);
            }
        }

        int[] answer = answerList.stream().mapToInt(i -> i).toArray();
        return answer;
    }

    static class WorkList {
        List<Work> works = new LinkedList<>();

        void add(Work work) {
            works.add(work);
        }

        int sprint() {
            int result = 0;
            boolean previousSuccess = true;

            for (Work work : works) {
                if (work.process() && previousSuccess) {
                    result++;
                } else {
                    previousSuccess = false;
                }
            }

            if (result != 0) {
                for (int i = 0; i < result; i++) {
                    works.remove(0);
                }
            }

            return result;
        }

        boolean hasNext() {
            return !works.isEmpty();
        }
    }

    static class Work {
        int progress;
        int speed;

        public Work(int progress, int speed) {
            this.progress = progress;
            this.speed = speed;
        }

        public boolean process() {
            this.progress += this.speed;
            if (this.progress >= 100) {
                return true;
            }
            return false;
        }
    }
}