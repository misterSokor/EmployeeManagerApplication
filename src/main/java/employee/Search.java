package employee;

import java.util.ArrayList;
import java.util.List;

public class Search {

    private String searchType;
    private String searchTerm;
    private List<Employee> results = new ArrayList<>();
    private boolean found;

    public Search() {
        // Empty constructor
    }

    public String getSearchType() {
        return searchType;
    }

    public void setSearchType(String searchType) {
        this.searchType = searchType;
    }

    public String getSearchTerm() {
        return searchTerm;
    }

    public void setSearchTerm(String searchTerm) {
        this.searchTerm = searchTerm;
    }

    public List<Employee> getResults() {
        return results;
    }

    public void setResults(List<Employee> results) {
        this.results = results;
    }

    public boolean isFound() {
        return found;
    }

    public void setFound(boolean found) {
        this.found = found;
    }

    public void addFoundEmployee(Employee employee) {
        results.add(employee);
        found = true;
    }
}
