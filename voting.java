import java.util.*;

public class voting {

    static Scanner sc = new Scanner(System.in);

    static HashMap<Integer, String> candidates = new HashMap<>();
    static HashMap<Integer, Integer> votes = new HashMap<>();
    static HashSet<String> voters = new HashSet<>();

    public static void main(String[] args) {

        try {
            takeCandidatesFromUser();
        } catch (Exception e) {
            System.out.println("❌ Error while adding candidates!");
            return;
        }

        int choice = 0;

        do {
            try {
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
                        System.out.println("⚠️ Invalid choice! Please enter 1–4.");
                }
            } catch (InputMismatchException e) {
                System.out.println("❌ Invalid input! Please enter numbers only.");
                sc.next(); // clear buffer
            }

        } while (choice != 4);
    }

    // 🟦 Candidate input with validation
    static void takeCandidatesFromUser() {
        int n = 0;

        while (true) {
            try {
                System.out.print("How many candidates do you want to add? ");
                n = sc.nextInt();

                if (n <= 0) {
                    System.out.println("⚠️ Number of candidates must be greater than 0.");
                    continue;
                }
                break;
            } catch (InputMismatchException e) {
                System.out.println("❌ Enter a valid number!");
                sc.next();
            }
        }

        sc.nextLine(); // buffer clear

        for (int i = 1; i <= n; i++) {
            System.out.print("Enter name of candidate " + i + ": ");
            String name = sc.nextLine();

            candidates.put(i, name);
            votes.put(i, 0);
        }
        System.out.println("\n✅ Candidates added successfully!");
    }

    static void castVote() {
        if (candidates.isEmpty()) {
            System.out.println("⚠️ No candidates available!");
            return;
        }

        System.out.print("\nEnter your Voter ID: ");
        String voterID = sc.next();

        if (voters.contains(voterID)) {
            System.out.println("⚠️ You have already voted!");
            return;
        }

        System.out.println("\n===== Candidates List =====");
        for (Map.Entry<Integer, String> entry : candidates.entrySet()) {
            System.out.println(entry.getKey() + ". " + entry.getValue());
        }

        try {
            System.out.print("Enter Candidate Number to vote: ");
            int selectedCandidate = sc.nextInt();

            if (candidates.containsKey(selectedCandidate)) {
                votes.put(selectedCandidate, votes.get(selectedCandidate) + 1);
                voters.add(voterID);
                System.out.println("✅ Vote cast successfully for "
                        + candidates.get(selectedCandidate));
            } else {
                System.out.println("❌ Invalid candidate number!");
            }
        } catch (InputMismatchException e) {
            System.out.println("❌ Please enter a valid candidate number!");
            sc.next();
        }
    }

    static void showVoteCount() {
        if (votes.isEmpty()) {
            System.out.println("⚠️ No votes cast yet!");
            return;
        }

        System.out.println("\n===== VOTE COUNT =====");
        for (Map.Entry<Integer, String> entry : candidates.entrySet()) {
            System.out.println(entry.getValue() + " : "
                    + votes.get(entry.getKey()) + " votes");
        }
    }

    static void showLeadingCandidate() {
        if (votes.isEmpty()) {
            System.out.println("⚠️ No votes available!");
            return;
        }

        int maxVotes = -1;
        String leader = "None";

        for (Map.Entry<Integer, Integer> entry : votes.entrySet()) {
            if (entry.getValue() > maxVotes) {
                maxVotes = entry.getValue();
                leader = candidates.get(entry.getKey());
            }
        }

        System.out.println("\n🏆 Leading Candidate: "
                + leader + " (" + maxVotes + " votes)");
    }
}
