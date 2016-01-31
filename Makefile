all: compile test

compile:
	javac -d . src/*.java

test:
	@echo "************************************************************************"
	java com.bitwisehero.course315.achievery.Main < tests/test_AchievementRanking.txt
	@echo "************************************************************************"
	java com.bitwisehero.course315.achievery.Main < tests/test_ComparePlayers.txt
	@echo "************************************************************************"
	java com.bitwisehero.course315.achievery.Main < tests/test_FriendsWhoPlay.txt
	@echo "************************************************************************"
	java com.bitwisehero.course315.achievery.Main < tests/test_SummarizeAchievement.txt
	@echo "************************************************************************"
	java com.bitwisehero.course315.achievery.Main < tests/test_SummarizeGame.txt
	@echo "************************************************************************"
	java com.bitwisehero.course315.achievery.Main < tests/test_SummarizePlayer.txt
	@echo "************************************************************************"

clean:
	rm -rf com
