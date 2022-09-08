package ir.mahdi.universityservice.exceptions;

public class ExamHaveBeenDoneByStudent extends RuntimeException {
    public ExamHaveBeenDoneByStudent() {
        super("Exam have been done by student!");
    }
}
