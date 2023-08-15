package employee;


import java.util.ArrayList;
import java.util.List;


public class Search {


    private String searchType;
    private String searchTerm;
    private List<employee.Employee> results = new ArrayList<>();
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


    public List<employee.Employee> getResults() {
        return results;
    }


    public void setResults(List<employee.Employee> results) {
        this.results = results;
    }


    public boolean isFound() {
        return found;
    }


    public void setFound(boolean found) {
        this.found = found;
    }


    public void addFoundEmployee(employee.Employee employee) {
        results.add(employee);
        found = true;
    }
}