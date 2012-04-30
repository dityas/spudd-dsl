package org.flyhighplato.spudder

class Spudder {
	private final Map<String, List<String>> variables, observations
	private final File outFile
	private final BufferedWriter writer
	
	public Spudder(String fileName, Map<String,List<String>> variableValues,Map<String,List<String>> observationValues) {
		variables = variableValues
		observations = observationValues
		outFile = new File(fileName)
		writer = outFile.newWriter(false)
	}
	
	public variables() {
		String output = ""
		output += "(variables \r\n"
		
		variables.keySet().each{ k ->
			output+="${SpudderUtil.INDENT}($k "
				variables[k].each{ v ->
					output+="${getVarName(k,v)} "
				}
			output+=")\r\n"
		}
		output += ")"
		
		writeLine(output)
	}
	
	public observations() {
		String output = ""
		output += "(observations \r\n"
		
		observations.keySet().each{ k ->
			output+="${SpudderUtil.INDENT}($k "
				observations[k].each{ v ->
					output+="${getVarName(k,v)} "
				}
			output+=")\r\n"
		}
		output += ")"
		
		writeLine(output)
	}
	
	public SpudderAction action(String actionName) {
		return new SpudderAction(this,actionName)
	}
	
	public SpuddActionTree actionTree() {
		return new SpuddActionTree(this,[""],[""])
	}
	
	public SpuddTree initBelief() {
		return new SpuddTree(this,[""],["init"])
	} 
	
	public SpuddTree dd(String ddName) {
		return new SpuddDDTree(this,[""],["$ddName"])
	}
	public void writeLine(String s) {
			writer.writeLine(s);
			writer.flush()
	}
	
	public void write(String s) {
		writer.write(s);
		writer.flush()
	}
	
	protected getVarName(String type, def ordinal) {
		return "${type}_${ordinal}"
	}
	
	final Map<String, List<String>> getVariables() {
		return variables
	}
	
	final Map<String, List<String>> getObservations() {
		return observations
	}
	
}
