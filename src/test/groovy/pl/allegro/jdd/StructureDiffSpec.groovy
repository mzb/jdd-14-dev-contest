package pl.allegro.jdd

import spock.lang.Specification

/**
 * @author bartosz walacik
 */
class StructureDiffSpec extends Specification{

    def "should calculate empty diff when nothing changed"() {
        given:
        def cto = new Employee("Big Boss").addSubordinates(new Employee("Some Manager"),
                                                           new Employee("Other Manager"))
        def theSameCto = new Employee("Big Boss").addSubordinates(new Employee("Some Manager"),
                                                                  new Employee("Other Manager"))

        when:
        def changes = new StructureDiff().calculate(cto, theSameCto)

        then:
        !changes.fired
        !changes.hired
        !changes.changedSalary
    }

    def "should detect salary change"() {
        given:
        def oldStruct = new Employee("Big Boss").addSubordinates(new Employee("Noisy Manager"),
                                                                 new Employee("Poor One", 20000))
        def newStruct = new Employee("Big Boss").addSubordinates(new Employee("Noisy Manager"),
                                                                 new Employee("Poor One", 10000))
        when:
        def changes = new StructureDiff().calculate(oldStruct, newStruct)

        then:
        !changes.fired
        !changes.hired
        changes.changedSalary.size() == 1
        changes.changedSalary[0].name == "Poor One"
    }

    def "should detect hired"() {
        given:
        def oldCto = new Employee("Old Boss")
        def newCto = new Employee("Big Boss").addSubordinates(new Employee("Hired One"),
                                                              new Employee("Hired Second"))

        when:
        def changes = new StructureDiff().calculate(oldCto, newCto)

        then:
        changes.fired.size() == 1
        !changes.changedSalary
        changes.hired.size() == 3
        changes.hired*.name as Set == ["Big Boss", "Hired One", "Hired Second"] as Set
    }

    def "should detect fired"() {
        given:
        def oldCto = new Employee("Old Boss").addSubordinates(new Employee("Fired One"),
                                                              new Employee("Fired Second"))
        def newCto = new Employee("Big Boss")

        when:
        def changes = new StructureDiff().calculate(oldCto, newCto)

        then:
        changes.hired.size() == 1
        !changes.changedSalary
        changes.fired.size() == 3
        changes.fired*.name as Set == ["Old Boss", "Fired One", "Fired Second"] as Set
    }

    def "should detect hired for large, flat structure"() {
        given:
        def oldCto = new Employee("Old Boss")
        def newCto = new Employee("Big Boss")

        10000.times { newCto.addSubordinate(new Employee("Emp no.$it")) }

        when:
        def changes = new StructureDiff().calculate(oldCto, newCto)

        then:
        changes.hired.size() == 10001
        changes.fired.size() == 1
        !changes.changedSalary
    }

    def "should detect fired for large, depth structure"() {
        given:
        def oldCto = new Employee("Old Boss")
        def newCto = new Employee("Big Boss")

        def boss = oldCto;
        10000.times {
            def emp = new Employee("Emp no.$it")
            boss.addSubordinate emp
            boss = emp
        }

        when:
        def changes = new StructureDiff().calculate(oldCto, newCto)

        then:
        changes.fired.size() == 10001
        changes.hired.size() == 1
        !changes.changedSalary
    }
}
