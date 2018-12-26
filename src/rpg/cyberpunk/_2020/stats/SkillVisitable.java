package rpg.cyberpunk._2020.stats;

public interface SkillVisitable {
	public CyberpunkSkill accept(SkillVisitor visitor);
}
