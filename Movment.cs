using UnityEngine;
using System.Collections;

public class Movment : MonoBehaviour {
	
	public float walk = 2f;
	public float run = 6f;
	public float jumpw = 2500f;
	public float jumpr = 3000f;
	//public float gravity = 20f;
	
	Vector3 movment;
	Vector3 turn;
	Animator anim;
	Rigidbody playerRidgedbody;
	//CharacterController controller;
	float GroundDistance = 0;
	Vector3 jumpv;
	AudioSource jumpsound;
	
	
	void Awake()
	{
		anim = GetComponent<Animator> ();
		playerRidgedbody = GetComponent<Rigidbody> ();
		jumpsound = GetComponent<AudioSource> ();
		//controller = GetComponent<CharacterController>();
		
	}
	
	void  FixedUpdate()
	{
		float h = Input.GetAxis ("Horizontal");
		float v = Input.GetAxis ("Vertical");
		bool shift = Input.GetKey (KeyCode.LeftShift);
		bool wave = Input.GetKeyDown (KeyCode.RightShift);
		bool jump = Input.GetButtonDown ("Jump");
		
		Move (v, shift);
		
		if (IsGrounded ()) {
			Turning (h);
			Jumping (shift, jump, v);
		}
		Animating (h, v, shift, wave, jump);
		//playerRidgedbody.AddForce (Vector3.up * gravity * -1);// * Time.deltaTime);
	}
	
	void Move(float v, bool shift)
	{
		movment.Set (0f, 0f, v);
		movment = transform.TransformDirection(movment);
		if (shift) {
			if (v > 0)
				movment = movment * run;
		}
		else {
			movment = movment * walk;
		}
		
		transform.localPosition += movment * Time.fixedDeltaTime;
	}
	
	void Turning (float h)
	{
		transform.Rotate (0, h * walk, 0);
	}
	
	void Jumping(bool shift ,bool jump, float v)
	{
		if (v > 0) {
			if (jump) {
				if (shift)
					playerRidgedbody.AddForce (Vector3.up * jumpr);
				else
					playerRidgedbody.AddForce (Vector3.up * jumpw);

				jumpsound.Play();
			}
		}
		
		
		// * Time.fixedDeltaTime);
		
	}
	
	void Animating (float h, float v, bool run, bool wave, bool jump)
	{
		bool walkb = v < 0, movef = v > 0;
		float isRun = 0f;
		if (run)
			isRun = 1;
		if(wave)
			anim.SetTrigger ("Wave");
		
		if (IsGrounded ()) {
			anim.SetBool ("Jump", false);
		} else {
			anim.SetBool ("Jump", true);
		}
		anim.SetFloat ("Direction", h);
		anim.SetFloat ("Run", isRun);
		anim.SetBool ("WalkB", walkb);
		anim.SetBool ("MoveF", movef);
		
	}
	
	bool IsGrounded ()
	{
		return Physics.Raycast (transform.position, - Vector3.up, GroundDistance + 0.05f);
	}
}








