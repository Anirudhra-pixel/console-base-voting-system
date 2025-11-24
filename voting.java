
    import java.util.*;

public class voting {

    static Scanner sc = new Scanner(System.in);

    static HashMap<Integer, String> candidates = new HashMap<>();
    static HashMap<Integer, Integer> votes = new HashMap<>();
    static HashSet<String> voters = new HashSet<>();

    public static void main(String[] args) {

        takeCandidatesFromUser();   // ← User input se candidates lenge
        int choice;

        do {
            System.out.println("\n===== ONLINE VOTING SYSTEM =====");
            System.out.println("1. Cast Vote");
            System.out.println("2. Show Vote Count");
            System.out.println("3. Show Leading Candidate");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    castVote();
                    break;
                case 2:
                    showVoteCount();
                    break;
                case 3:
                    showLeadingCandidate();
                    break;
                case 4:
                    System.out.println("Thank you for using the Voting System!");
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }

        } while (choice != 4);
    }

    // 🟦 User se candidate details lena
    static void takeCandidatesFromUser() {
        System.out.print("How many candidates do you want to add? ");
        int n = sc.nextInt();

        sc.nextLine(); // buffer clear

        for (int i = 1; i <= n; i++) {
            System.out.print("Enter name of candidate " + i + ": ");
            String name = sc.nextLine();

            candidates.put(i, name);
            votes.put(i, 0);
        }
        System.out.println("\nCandidates added successfully!");
    }

    static void castVote() {

        System.out.print("\nEnter your Voter ID: ");
        String voterID = sc.next();

        if (voters.contains(voterID)) {
            System.out.println("⚠️  You have already voted!");
            return;
        }

        System.out.println("\n===== Candidates List =====");
        for (Map.Entry<Integer, String> entry : candidates.entrySet()) {
            System.out.println(entry.getKey() + ". " + entry.getValue());
        }

        System.out.print("Enter Candidate Number to vote: ");
        int selectedCandidate = sc.nextInt();

        if (candidates.containsKey(selectedCandidate)) {
            votes.put(selectedCandidate, votes.get(selectedCandidate) + 1);
            voters.add(voterID);
            System.out.println("✅ Vote cast successfully for " + candidates.get(selectedCandidate) + "!");
        } else {
            System.out.println("❌ Invalid candidate number!");
        }
    }

    static void showVoteCount() {
        System.out.println("\n===== VOTE COUNT =====");
        for (Map.Entry<Integer, String> entry : candidates.entrySet()) {
            System.out.println(entry.getValue() + " : " + votes.get(entry.getKey()) + " votes");
        }
    }

    static void showLeadingCandidate() {
        int maxVotes = -1;
        String leader = "";

        for (Map.Entry<Integer, Integer> entry : votes.entrySet()) {
            if (entry.getValue() > maxVotes) {
                maxVotes = entry.getValue();
                leader = candidates.get(entry.getKey());
            }
        }

        System.out.println("\n🏆 Leading Candidate: " + leader + " (" + maxVotes + " votes)");
    }
}


