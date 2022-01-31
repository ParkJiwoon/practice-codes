package template_method._02_after;

import template_method._02_after.code.AbstractTemplate;
import template_method._02_after.code.SubClassLogic1;
import template_method._02_after.code.SubClassLogic2;

public class AfterTemplateMethodApp {

    public static void main(String[] args) {
        AbstractTemplate template1 = new SubClassLogic1();
        template1.execute();

        AbstractTemplate template2 = new SubClassLogic2();
        template2.execute();
    }
}
