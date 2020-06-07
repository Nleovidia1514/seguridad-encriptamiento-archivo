package com.labc.Endpoints;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.security.PrivateKey;
import java.security.PublicKey;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.labc.Utils.Encrypter;

/**
 * Servlet implementation class Decrypt
 */
@MultipartConfig()
public class Decrypt extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public Decrypt() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		PrintWriter out = response.getWriter();
		try {
			String message = request.getParameter("encryptedFile");
			System.out.println("Encrypted Message: " + message);
			// create ObjectOutputStream object
			String decrypted = decrypt(message);
			System.out.println("Decrypted Message: " + decrypted);
			// write object to Socket
			out.write(decrypted);
		} catch (Exception e) {
			e.printStackTrace();
			response.setStatus(500);
			out.write("Ha ocurrido un error en el servidor: " + e.getMessage());
		}
		
	}

	private static String decrypt(String encrypted_msg) {
		Encrypter ac;
		String decrypted_msg = null;
		try {
			ac = new Encrypter();
			PrivateKey publicKey = ac.getPrivate("KeyPair/privateKey");
			decrypted_msg = ac.decryptText(encrypted_msg, publicKey);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return decrypted_msg;

	}

}
