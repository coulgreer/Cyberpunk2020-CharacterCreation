package rpg.cyberpunk._2020.stats;

public interface SkillVisitor {
	public CyberpunkSkill visit(SpecializedSkill skill);
	
	public CyberpunkSkill visit(BroadSkill skill);
	
	public CyberpunkSkill visit(RoleSkill skill);
}
